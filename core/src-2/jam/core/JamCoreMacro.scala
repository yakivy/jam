package jam.core

import scala.reflect.macros.TypecheckException
import scala.reflect.macros.blackbox.Context

private[jam] class JamCoreMacro(val c: Context) {
    import c.universe._

    def brewImpl[J: c.WeakTypeTag](config: c.Tree): c.Expr[J] = {
        brewFromImpl(q"this")(config)
    }

    def brewFromImpl[J: c.WeakTypeTag](self: c.Tree)(config: c.Tree): c.Expr[J] = {
        val candidates = findCandidates(self)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe
        val (constructorArgs, createFun, _) = getConstructorArgumentsAndCreateFunction(tpe, ftpe = None, prefix = "")
        c.Expr(brew(self, tpe, ftpe = None, candidates, constructorArgs, prefix = "")(
            abortOnVacancy,
            createFun,
            pure = None,
        ))
    }

    def brewWithImpl[J: c.WeakTypeTag](f: c.Tree)(config: c.Tree): c.Expr[J] = brewFromWithImpl(q"this", f)(config)

    def brewFromWithImpl[J: c.WeakTypeTag](self: c.Tree, f: c.Tree)(config: c.Tree): c.Expr[J] = {
        val candidates = findCandidates(self)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe
        c.Expr(brew(self, tpe, ftpe = None, candidates, getFunctionArguments(f), prefix = "")(
            abortOnVacancy,
            createResultFromFunction(f, _),
            pure = None,
        ))
    }

    def brewRecImpl[J: c.WeakTypeTag](config: c.Tree): c.Expr[J] = brewFromRecImpl(q"this")(config)

    def brewFromRecImpl[J: c.WeakTypeTag](self: c.Tree)(config: c.Tree): c.Expr[J] = {
        val candidates = findCandidates(self)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe
        val configR = parseConfig(config)
        val (constructorArgs, createFun, _) = getConstructorArgumentsAndCreateFunction(tpe, ftpe = None, prefix = "")
        c.Expr(brew(self, tpe, ftpe = None, candidates, constructorArgs, prefix = "")(
            createRecOnVacancy(self, configR, candidates),
            createFun,
            pure = None,
        ))
    }

    private[jam] def parseConfig(config: c.Tree): JamDsl#JamConfig = config match {
        case Typed(Apply(_, List(Literal(Constant(brewRecRegex: String)))), _) =>
            new jam.JamConfig(brewRecRegex)
        case _ => c.abort(
            c.enclosingPosition,
            "Unable to eval JamConfig at compile time. " +
                "Provided value should be `macro def` and in " +
                "`: c.Tree = c.universe.reify(new JamConfig(...)).tree` format"
        )
    }

    private[jam] def validateBrewRecType(tpe: c.Type, config: JamDsl#JamConfig, prefix: String): Unit = {
        if (!tpe.toString.matches(config.brewRecRegex)) c.abort(
            c.enclosingPosition,
            s"Recursive brewing for instance $prefix($tpe) is prohibited from config. " +
                s"${tpe.toString} doesn't match ${config.brewRecRegex} regex."
        )
    }

    def brewWithRecImpl[J: c.WeakTypeTag](f: c.Tree)(config: c.Tree): c.Expr[J] =
        brewFromWithRecImpl(q"this", f)(config)

    def brewFromWithRecImpl[J: c.WeakTypeTag](self: c.Tree, f: c.Tree)(config: c.Tree): c.Expr[J] = {
        val candidates = findCandidates(self)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe
        val configR = parseConfig(config)
        c.Expr(brew(self, tpe, ftpe = None, candidates, getFunctionArguments(f), prefix = "")(
            createRecOnVacancy(self, configR, candidates),
            createResultFromFunction(f, _),
            pure = None,
        ))
    }

    private[jam] def createResultFromConstructor(tpe: c.Type, args: List[List[(c.Type, c.Tree)]]): c.Tree =
        q"new ${tpe.dealias}(...${args.map(_.map(_._2))})"

    private[jam] def createResultFromCompanionConstructor(tpe: c.Type, args: List[List[(c.Type, c.Tree)]]): c.Tree =
        q"${tpe.typeSymbol.companion}.apply(...${args.map(_.map(_._2))})"

    private[jam] def createResultFromFunction(f: c.Tree, args: List[List[(c.Type, c.Tree)]]): c.Tree =
        q"$f(...${args.map(_.map(_._2))})"

    private def validTpe(tpe: c.Type): Boolean =
        tpe != null &&
            tpe != NoType &&
            tpe.typeSymbol != NoSymbol &&
            tpe.typeSymbol.name != termNames.ERROR

    private def resolveTermType(t: c.universe.ValOrDefDef): Option[c.Type] = {
        Option(t.tpt.tpe).filter(validTpe)
            .orElse(Option(t.tpt).map(t => c.typecheck(t, c.TYPEmode, silent = true).tpe).filter(validTpe))
    }

    protected def findCandidates(self: c.Tree): List[(c.universe.TermSymbol, c.universe.Type)] = {
        val defs: Map[TermName, Type] = c.enclosingImpl.collect {
            case t: DefDef if t.tparams.isEmpty && t.vparamss.flatten.isEmpty => t
            case t: ValDef => t
        }.filterNot(_.mods.hasFlag(Flag.PARAM)).flatMap(t => resolveTermType(t).map(t.name -> _)).toMap
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
            .flatMap { m =>
                val typeSignature = defs.get(m.name).orElse(Option(m.typeSignatureIn(selfType).resultType).filter(validTpe))
                if (typeSignature.isEmpty && (!m.isMethod || m.asMethod.paramLists.flatten.isEmpty)) c.abort(
                    c.enclosingPosition,
                    s"Unable to resolve the type for ${m.fullName}, " +
                        s"try to fix remaining compilation errors or provide type explicitly"
                )
                typeSignature.map(m -> _)
            }
            .filter(m => !(m._2 =:= typeOf[Nothing]) && !(m._2 =:= typeOf[Null]))
            .toList
    }

    private[jam] def getConstructorArguments(tpe: c.Type, prefix: String): Option[List[List[c.Symbol]]] = {
        val constructors = tpe.members
            .filter(m => m.isMethod && m.isPublic).map(_.asMethod)
            .filter(m => m.isConstructor && m.typeSignatureIn(tpe).finalResultType =:= tpe)
        val annotatedConstructors = constructors
            .filter(_.annotations.exists(_.tree.tpe.typeSymbol.fullName == "javax.inject.Inject"))
        val primaryConstructors = if (annotatedConstructors.nonEmpty) annotatedConstructors else constructors
        if (primaryConstructors.size > 1)
            c.abort(c.enclosingPosition, s"More than one primary constructor was found for $prefix($tpe)")
        primaryConstructors.headOption.map(_.paramLists)
    }

    private[jam] def getCompanionConstructorArguments(
        jtpe: c.Type,
        ftpe: Option[c.Type],
        prefix: String,
    ): Option[(List[List[c.Symbol]], Boolean)] = {
        val companionApplies = Option(jtpe.companion)
            .filter(validTpe)
            .fold(Iterable.empty[Symbol])(_.members)
            .filter(m => m.isMethod && m.isPublic).map(_.asMethod)
            .filter(m => m.name.decodedName.toString == "apply")
        val fConstructors = ftpe.map(t => appliedType(t.typeConstructor, jtpe))
            .map(fatpe => companionApplies.filter(m => m.returnType <:< fatpe))
            .getOrElse(Nil)
        def constructors = companionApplies.filter(m => m.returnType <:< jtpe)
        val primaryConstructors = if (fConstructors.nonEmpty) fConstructors else constructors
        if (primaryConstructors.size > 1)
            c.abort(c.enclosingPosition, s"More than one primary apply method was found for $prefix($jtpe)")
        primaryConstructors.headOption.map(_.paramLists -> fConstructors.nonEmpty)
    }

    private[jam] def getConstructorArgumentsAndCreateFunction(
        jtpe: c.Type,
        ftpe: Option[c.Type],
        prefix: String,
    ): (List[List[c.Symbol]], List[List[(c.Type, c.Tree)]] => c.Tree, Boolean) = {
        getCompanionConstructorArguments(jtpe, ftpe, prefix)
            .map { case (args, flat) => (args, createResultFromCompanionConstructor(jtpe, _), flat) }
            .orElse(
                getConstructorArguments(jtpe, prefix)
                    .map(args => (args, createResultFromConstructor(jtpe, _), false))
            )
            .getOrElse(
                c.abort(c.enclosingPosition, s"Unable to find public constructor or apply method for $prefix($jtpe)")
            )
    }

    private[jam] def getFunctionArguments(f: c.Tree): List[List[c.Symbol]] = f match {
        case Block(Nil, Function(args, _)) => List(args.map(_.symbol))
        case Function(args, _) => List(args.map(_.symbol))
        case _ => c.abort(c.enclosingPosition, s"Unsupported function type ${f.tpe}")
    }

    private def inferImplicit[A: c.universe.Liftable](tpe: A): Option[c.Tree] = {
        try c.typecheck(q"""_root_.scala.Predef.implicitly[$tpe]""") match {
            case EmptyTree => None
            case tree => Option(tree)
        }
        catch {
            case e: TypecheckException if e.msg.contains("could not find implicit value for") => None
            case e: TypecheckException => c.abort(c.enclosingPosition, e.msg)
        }
    }

    private[jam] def abortOnVacancy(tpe: c.Type, prefix: String): c.Tree =
        c.abort(c.enclosingPosition, s"Unable to find instance for $prefix($tpe)")

    private def createRecOnVacancy(
        self: c.Tree,
        config: JamDsl#JamConfig,
        candidates: List[(c.universe.TermSymbol, c.universe.Type)],
    )(tpe: c.Type, prefix: String): c.Tree = {
        def rec(tpe: c.Type, prefix: String): c.Tree = {
            validateBrewRecType(tpe, config, prefix)
            val (constructorArgs, createFun, _) = getConstructorArgumentsAndCreateFunction(tpe, ftpe = None, prefix)
            brew(self, tpe, ftpe = None, candidates, constructorArgs, prefix)(
                rec(_, _),
                createFun,
                pure = None,
            )
        }
        rec(tpe, prefix)
    }

    private[jam] def brew(
        self: c.Tree,
        jtpe: c.Type,
        ftpe: Option[c.Type],
        candidates: List[(c.universe.TermSymbol, c.universe.Type)],
        arguments: List[List[c.Symbol]],
        prefix: String,
    )(
        resolveVacancy: (c.Type, String) => c.Tree,
        createResult: List[List[(c.Type, c.Tree)]] => c.Tree,
        pure: Option[c.Tree => c.Tree],
    ): c.Tree = {
        val resolvedArguments = arguments.map(l => l.headOption.exists(_.isImplicit) -> l).map { case (impl, l) => l.map { p =>
            val ptpe = p.typeSignature.substituteTypes(jtpe.typeSymbol.asType.typeParams, jtpe.typeArgs)
            val pftpe = ftpe.map(t => appliedType(t.typeConstructor, ptpe))
            ptpe -> (if (impl) {
                inferImplicit(tq"${p.typeSignature}").getOrElse(
                    c.abort(
                        c.enclosingPosition,
                        s"Unable to resolve implicit instance for $prefix($jtpe).${p.name}($ptpe)"
                    )
                )
            } else {
                val parameterCandidates = candidates.filter { m =>
                    (m._2.finalResultType <:< ptpe || pftpe.exists(m._2.finalResultType <:< _)) &&
                        (!m._1.isMethod || m._1.asMethod.paramLists.flatten.isEmpty)
                }
                if (parameterCandidates.size > 1) c.abort(
                    c.enclosingPosition,
                    s"More than one injection candidate was found for $prefix($jtpe).${p.name}($ptpe): " +
                        s"${parameterCandidates.map(c => s"${c._1.fullName}(${c._2})").sorted.mkString(", ")}"
                )
                parameterCandidates.headOption.fold(
                    resolveVacancy(ptpe, s"$prefix($jtpe).${p.name}")
                ) { m =>
                    val arg = q"$self.${m._1.name}"
                    if (pftpe.forall(m._2.finalResultType <:< _)) arg else pure.get(arg)
                }
            })
        }}
        createResult(resolvedArguments)
    }
}
