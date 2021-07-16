package jam

import scala.reflect.macros.blackbox.Context

object JamMacro {
    def brewImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        val candidates = findCandidates(c)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe.dealias
        brew(c)(
            tpe, candidates, getConstructorArguments(c)(tpe, ""), "")(
            (tpe, prefix) => c.abort(c.enclosingPosition, s"Unable to find instance for $prefix($tpe)"),
            createResultFromConstructor(c)(tpe, _)
        )
    }

    def brewFImpl[J: c.WeakTypeTag](c: Context)(f: c.Tree): c.Tree = {
        val candidates = findCandidates(c)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe.dealias
        brew(c)(
            tpe, candidates, getFunctionArguments(c)(f), "")(
            (tpe, prefix) => c.abort(c.enclosingPosition, s"Unable to find instance for $prefix($tpe)"),
            createResultFromFunction(c)(f, _)
        ).tree
    }

    def brewTreeImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        val candidates = findCandidates(c)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe.dealias
        brew(c)(
            tpe, candidates, getConstructorArguments(c)(tpe, ""), "")(
            (tpe, prefix) => {
                def rec(tpe: c.Type, prefix: String): c.Tree = brew(c)(
                    tpe, candidates, getConstructorArguments(c)(tpe, prefix), prefix)(
                    rec(_, _), createResultFromConstructor(c)(tpe, _)
                ).tree
                rec(tpe, prefix)
            },
            createResultFromConstructor(c)(tpe, _)
        )
    }

    def brewFTreeImpl[J: c.WeakTypeTag](c: Context)(f: c.Tree): c.Expr[J] = {
        val candidates = findCandidates(c)
        val tpe = implicitly[c.WeakTypeTag[J]].tpe.dealias
        brew(c)(
            tpe, candidates, getFunctionArguments(c)(f), "")(
            (tpe, prefix) => {
                def rec(tpe: c.Type, prefix: String): c.Tree = brew(c)(
                    tpe, candidates, getConstructorArguments(c)(tpe, prefix), prefix)(
                    rec(_, _), createResultFromConstructor(c)(tpe, _)
                ).tree
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

    private def findCandidates(c: Context): List[(c.universe.TermSymbol, c.universe.Type)] = {
        import c.universe._
        val defs: Map[TermName, Type] = c.enclosingImpl.collect {
            case DefDef(_, n, _, Nil, tpt, rhs) => (n, tpt, rhs)
            case ValDef(_, n, tpt, rhs) => (n, tpt, rhs)
        }.flatMap(m => Option(m._2.tpe)
            .orElse(Option(m._2).filter(_ != EmptyTree).map(t =>
                c.typecheck(q"$t", c.TYPEmode, silent = true, withMacrosDisabled = true).tpe
            ))
            .orElse(Option(m._3).filter(_ != EmptyTree).map(
                c.typecheck(_, silent = true, withMacrosDisabled = true).tpe
            ))
            .map(m._1 -> _)
        ).toMap
        c.typecheck(q"this").tpe.members
            .filter(m => !m.fullName.startsWith("java.lang.Object") && !m.fullName.startsWith("scala.Any"))
            .filter(_.isTerm).map(_.asTerm).map(s => s.getter.orElse(s).asTerm).toList.distinct
            .map(m => m -> defs.getOrElse(m.name, m.typeSignature))
            .filter(m => !(m._2 =:= typeOf[Nothing]) && !(m._2 =:= typeOf[Null]))
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

    private def brew[J](
        c: Context)(
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
                q"""def implicitlyWithMessage[A](implicit @_root_.scala.annotation.implicitNotFound(${
                    s"Unable to resolve implicit instance for $prefix($tpe).${p.name}"
                }) value: A): A = value
               implicitlyWithMessage[${p.typeSignature}]"""
            } else {
                val parameterCandidates = candidates.filter { m =>
                    m._2.finalResultType <:< ptype && (!m._1.isMethod || m._1.asMethod.paramLists.flatten.isEmpty)
                }
                if (parameterCandidates.size > 1)
                    c.abort(c.enclosingPosition, s"More than one injection candidate was found for $prefix($tpe).${p.name}")
                parameterCandidates.headOption.fold(
                    resolveVacancy(ptype, s"$prefix($tpe).${p.name}"))(
                    m => q"this.${m._1.name}"
                )
            }
        }}
        c.Expr(createResult(resolvedArguments))
    }
}
