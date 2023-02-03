package jam.core

import jam.JamDsl
import scala.reflect.macros.TypecheckException
import scala.reflect.macros.blackbox.Context

object JamMacro {
    def brewImpl[J: c.WeakTypeTag](c: Context)(config: c.Tree): c.Expr[J] = {
        import c.universe._
        brewFromImpl(c)(q"this")(config)
    }

    def brewFromImpl[J: c.WeakTypeTag](c: Context)(self: c.Tree)(config: c.Tree): c.Expr[J] = {
        val candidates = findCandidates(c)(self)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe.dealias
        brew(c)(
            self, tpe, candidates, getConstructorArguments(c)(tpe, ""), "")(
            (tpe, prefix) => c.abort(c.enclosingPosition, s"Unable to find instance for $prefix($tpe)"),
            createResultFromConstructor(c)(tpe, _)
        )
    }

    def brewWithImpl[J: c.WeakTypeTag](c: Context)(f: c.Tree)(config: c.Tree): c.Expr[J] = {
        import c.universe._
        brewWithFromImpl(c)(f)(q"this")(config)
    }

    def brewWithFromImpl[J: c.WeakTypeTag](c: Context)(f: c.Tree)(self: c.Tree)(config: c.Tree): c.Expr[J] = {
        val candidates = findCandidates(c)(self)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe.dealias
        brew(c)(
            self, tpe, candidates, getFunctionArguments(c)(f), "")(
            (tpe, prefix) => c.abort(c.enclosingPosition, s"Unable to find instance for $prefix($tpe)"),
            createResultFromFunction(c)(f, _)
        )
    }

    def brewRecImpl[J: c.WeakTypeTag](c: Context)(config: c.Tree): c.Expr[J] = {
        import c.universe._
        brewFromRecImpl(c)(q"this")(config)
    }

    def brewFromRecImpl[J: c.WeakTypeTag](c: Context)(self: c.Tree)(config: c.Tree): c.Expr[J] = {
        val candidates = findCandidates(c)(self)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe.dealias
        val configR = parseConfig(c)(config)
        brew(c)(
            self, tpe, candidates, getConstructorArguments(c)(tpe, ""), "")(
            (tpe, prefix) => {
                def rec(tpe: c.Type, prefix: String): c.Tree = {
                    validateBrewRecType(c)(tpe, configR, prefix)
                    brew(c)(
                        self, tpe, candidates, getConstructorArguments(c)(tpe, prefix), prefix)(
                        rec(_, _), createResultFromConstructor(c)(tpe, _)
                    ).tree
                }
                rec(tpe, prefix)
            },
            createResultFromConstructor(c)(tpe, _)
        )
    }

    private def parseConfig(c: Context)(config: c.Tree): JamDsl#JamConfig = {
        import c.universe._
        config match {
            case Typed(Apply(_, List(Literal(Constant(brewRecRegex: String)))), _) =>
                new jam.JamConfig(brewRecRegex)
            case _ => c.abort(
                c.enclosingPosition,
                "Unable to eval JamConfig at compile time. " +
                    "Provided value should be `macro def` and in " +
                    "`: c.Tree = c.universe.reify(new JamConfig(...)).tree` format"
            )
        }
    }

    private def validateBrewRecType(c: Context)(tpe: c.Type, config: JamDsl#JamConfig, prefix: String): Unit = {
        if (!tpe.toString.matches(config.brewRecRegex)) c.abort(
            c.enclosingPosition,
            s"Recursive brewing for instance $prefix($tpe) is prohibited from config. " +
                s"${tpe.toString} doesn't match ${config.brewRecRegex} regex."
        )
    }

    def brewWithRecImpl[J: c.WeakTypeTag](c: Context)(f: c.Tree)(config: c.Tree): c.Expr[J] = {
        import c.universe._
        brewWithFromRecImpl(c)(f)(q"this")(config)
    }

    def brewWithFromRecImpl[J: c.WeakTypeTag](c: Context)(f: c.Tree)(self: c.Tree)(config: c.Tree): c.Expr[J] = {
        val candidates = findCandidates(c)(self)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe.dealias
        val configR = parseConfig(c)(config)
        brew(c)(
            self, tpe, candidates, getFunctionArguments(c)(f), "")(
            (tpe, prefix) => {
                def rec(tpe: c.Type, prefix: String): c.Tree = {
                    validateBrewRecType(c)(tpe, configR, prefix)
                    brew(c)(
                        self, tpe, candidates, getConstructorArguments(c)(tpe, prefix), prefix)(
                        rec(_, _), createResultFromConstructor(c)(tpe, _)
                    ).tree
                }
                rec(tpe, prefix)
            },
            createResultFromFunction(c)(f, _)
        )
    }

    private def createResultFromConstructor(c: Context)(tpe: c.Type, args: List[List[c.Tree]]): c.Tree = {
        import c.universe._
        q"new $tpe(...$args)"
    }

    private def createResultFromFunction(c: Context)(f: c.Tree, args: List[List[c.Tree]]): c.Tree = {
        import c.universe._
        q"$f(...$args)"
    }

    private def validTpe(c: Context)(tpe: c.Type): Boolean = {
        import c.universe._
        tpe != null &&
            tpe != NoType &&
            tpe.typeSymbol != NoSymbol &&
            tpe.typeSymbol.name != termNames.ERROR
    }

    private def resolveTermType(c: Context)(t: c.universe.ValOrDefDef): Option[c.Type] = {
        import c.universe._
        Option(t.tpt.tpe).filter(validTpe(c))
            .orElse(Option(t.tpt).map(t => c.typecheck(q"$t", c.TYPEmode, silent = true).tpe).filter(validTpe(c)))
            .orElse(Option(t.rhs.tpe).filter(validTpe(c)))
            .orElse(Option(t.rhs).map(t => c.typecheck(t, silent = true, withMacrosDisabled = true).tpe).filter(validTpe(c)))
    }

    private def findCandidates(c: Context)(self: c.Tree): List[(c.universe.TermSymbol, c.universe.Type)] = {
        import c.universe._
        val defs: Map[TermName, Type] = c.enclosingImpl.collect {
            case t: DefDef if t.tparams.isEmpty && t.vparamss.flatten.isEmpty => t
            case t: ValDef => t
        }.flatMap(t => resolveTermType(c)(t).map(t.name -> _)).toMap
        val selfType = c.typecheck(self).tpe
        selfType
            .members
            .view
            .filter(m => !m.fullName.startsWith("java.lang.Object.") && !m.fullName.startsWith("scala.Any."))
            //.asMethod resolves signature therefore asks to provide explicit types for brewed members
            .filter(m => !m.isConstructor/* && (!m.isMethod || m.asMethod.paramLists.flatten.isEmpty)*/)
            .filter(_.isTerm).map(_.asTerm)
            .map(s => s.getter.orElse(s).asTerm)
            .toList.distinct.view
            .flatMap(m => {
                val typeSignature = defs.get(m.name).orElse(Option(m.typeSignatureIn(selfType)).filter(validTpe(c)))
                if(typeSignature.isEmpty && (!m.isMethod || m.asMethod.paramLists.flatten.isEmpty)) c.abort(
                    c.enclosingPosition,
                    s"Unable to resolve the type for ${m.fullName}, " +
                        s"try to fix remain compilation errors or provide type explicitly"
                )
                typeSignature.map(m -> _)
            }

            )
            .filter(m => !(m._2 =:= typeOf[Nothing]) && !(m._2 =:= typeOf[Null]))
            .toList
    }

    private def getConstructorArguments(c: Context)(tpe: c.Type, prefix: String): List[List[c.Symbol]] = {
        val constructors = tpe.members
            .filter(m => m.isMethod && m.isPublic).map(_.asMethod)
            .filter(m => m.isConstructor && m.typeSignatureIn(tpe).finalResultType =:= tpe)
        if (constructors.isEmpty)
            c.abort(c.enclosingPosition, s"Unable to find public constructor for $prefix($tpe)")
        val annotatedConstructors = constructors.filter(_.annotations.exists(_.tree.tpe.typeSymbol.fullName == "javax.inject.Inject"))
        val primaryConstructors = if (annotatedConstructors.nonEmpty) annotatedConstructors else constructors
        if (primaryConstructors.size > 1)
            c.abort(c.enclosingPosition, s"More than one primary constructor was found for $prefix($tpe)")
        primaryConstructors.head.paramLists
    }

    private def getFunctionArguments(c: Context)(f: c.Tree): List[List[c.Symbol]] = {
        import c.universe._
        f match {
            case Block(Nil, Function(args, _)) => List(args.map(_.symbol))
            case Function(args, _) => List(args.map(_.symbol))
            case _ => c.abort(c.enclosingPosition, s"Unsupported function type ${f.tpe}")
        }
    }

    def inferImplicit[A: c.universe.Liftable](c: Context)(tpe: A): Option[c.Tree] = {
        import c.universe._
        try c.typecheck(q"""_root_.scala.Predef.implicitly[$tpe]""") match {
            case EmptyTree => None
            case tree => Option(tree)
        }
        catch {
            case e: TypecheckException if e.msg.contains("could not find implicit value for") => None
            case e: TypecheckException => c.abort(c.enclosingPosition, e.msg)
        }
    }

    private def brew[J](
        c: Context)(
        self: c.Tree,
        tpe: c.Type,
        candidates: List[(c.universe.TermSymbol, c.universe.Type)],
        arguments: List[List[c.Symbol]],
        prefix: String)(
        resolveVacancy: (c.Type, String) => c.Tree,
        createResult: List[List[c.Tree]] => c.Tree
    ): c.Expr[J] = {
        import c.universe._
        val resolvedArguments = arguments.map(l => l.headOption.exists(_.isImplicit) -> l).map { case (impl, l) => l.map { p =>
            val ptype = p.typeSignature.substituteTypes(tpe.typeSymbol.asClass.typeParams, tpe.typeArgs)
            if (impl) {
                inferImplicit(c)(tq"${p.typeSignature}").getOrElse(
                    c.abort(
                        c.enclosingPosition,
                        s"Unable to resolve implicit instance for $prefix($tpe).${p.name}"
                    )
                )
            } else {
                val parameterCandidates = candidates.filter { m =>
                    m._2.finalResultType <:< ptype && (!m._1.isMethod || m._1.asMethod.paramLists.flatten.isEmpty)
                }
                if (parameterCandidates.size > 1) c.abort(
                    c.enclosingPosition,
                    s"More than one injection candidate was found for $prefix($tpe).${p.name}: " +
                        s"${parameterCandidates.map(_._1.fullName).sorted}"
                )
                parameterCandidates.headOption.fold(
                    resolveVacancy(ptype, s"$prefix($tpe).${p.name}"))(
                    m => q"$self.${m._1.name}"
                )
            }
        }}
        c.Expr(createResult(resolvedArguments))
    }
}
