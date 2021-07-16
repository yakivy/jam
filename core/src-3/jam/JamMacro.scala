package jam

import scala.quoted.*

object JamMacro {
    def brewImpl[J](using q: Quotes)(using Type[J]): Expr[J] = {
        import q.reflect.*
        val ttr = TypeTree.of[J]
        val self = findSelf
        val constructor = getConstructor(ttr, "")
        val candidates = findCandidates(self)
        brew(ttr, self, constructor._2, candidates, "")(
            (ttr, prefix) => report.throwError(s"Unable to find instance for $prefix(${ttr.show})"),
            createResultFromConstructor(ttr, constructor._1, _)
        ).asExprOf
    }

    def brewFImpl[J](using q: Quotes)(using Type[J])(f: Expr[?]): Expr[J] = {
        import q.reflect.*
        val ttr = TypeTree.of[J]
        val self = findSelf
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self)
        brew(ttr, self, constructor, candidates, "")(
            (ttr, prefix) => report.throwError(s"Unable to find instance for $prefix(${ttr.show})"),
            createResultFromFunction(f.asTerm, _)
        ).asExprOf
    }

    def brewTreeImpl[J](using q: Quotes)(using Type[J]): Expr[J] = {
        import q.reflect.*
        val ttr = TypeTree.of[J]
        val self = findSelf
        val constructor = getConstructor(ttr, "")
        val candidates = findCandidates(self)
        brew(ttr, self, constructor._2, candidates, "")(
            (ttr, prefix) => {
                def rec(ttr: TypeTree, typePrefix: String): q.reflect.Term = {
                    val constructor = getConstructor(ttr, prefix)
                    brew(
                        ttr, self, constructor._2, candidates, typePrefix)(
                        rec(_, _), createResultFromConstructor(ttr, constructor._1, _)
                    ).asTerm
                }
                rec(ttr, prefix)
            },
            createResultFromConstructor(ttr, constructor._1, _)
        ).asExprOf
    }

    def brewFTreeImpl[J](using q: Quotes)(using Type[J])(f: Expr[?]): Expr[J] = {
        import q.reflect.*
        val ttr = TypeTree.of[J]
        val self = findSelf
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self)
        brew(ttr, self, constructor, candidates, "")(
            (ttr, prefix) => {
                def rec(ttr: TypeTree, typePrefix: String): q.reflect.Term = {
                    val constructor = getConstructor(ttr, prefix)
                    brew(
                        ttr, self, constructor._2, candidates, typePrefix)(
                        rec(_, _), createResultFromConstructor(ttr, constructor._1, _)
                    ).asTerm
                }
                rec(ttr, prefix)
            },
            createResultFromFunction(f.asTerm, _)
        ).asExprOf
    }

    private def findSelf(using q: Quotes): q.reflect.This = {
        import q.reflect.*
        def rec(s: Symbol): Option[Symbol] = s.maybeOwner match {
            case o if o.isNoSymbol => None
            case o if o.isClassDef || o.isPackageDef => Option(o)
            case o => rec(o)
        }
        rec(Symbol.spliceOwner).map(This.apply)
            .getOrElse(report.throwError(s"Unable to access 'this'"))
    }

    private def findCandidates(
        using q: Quotes)(
        self: q.reflect.This
    ): List[(q.reflect.Symbol, List[List[Nothing]], q.reflect.TypeRepr)] = {
        import q.reflect.*
        (self.tpe.typeSymbol.memberMethods ::: self.tpe.typeSymbol.memberFields)
            .filter(m => !m.fullName.startsWith("java.lang.Object") && !m.fullName.startsWith("scala.Any"))
            .map(_.tree).collect {
                case m: ValDef => (m.symbol, Nil, m.rhs.map(_.tpe).getOrElse(m.tpt.tpe))
                case m: DefDef if m.termParamss.flatMap(_.params).isEmpty =>
                    (m.symbol, m.termParamss.map(_ => Nil), m.rhs.map(_.tpe).getOrElse(m.returnTpt.tpe))
            }
            .filter(m => !(m._3 =:= TypeRepr.of[Nothing]) && !(m._3 =:= TypeRepr.of[Null]))
    }

    private def getConstructor(
        using q: Quotes)(ttr: q.reflect.TypeTree, prefix: String
    ): (q.reflect.Symbol, List[(Boolean, List[q.reflect.ValDef])]) = {
        import q.reflect.*
        val ttrArgs = getTtrArguments(ttr)
        val constructors = ttr.tpe.typeSymbol.declarations
            .filter(m => m.isClassConstructor).map(_.tree)
            .collect { case m: DefDef if ttrArgs.fold(
                m.returnTpt.tpe)(
                args => m.returnTpt.tpe.asInstanceOf[AppliedType].tycon.appliedTo(args.values.toList)
            ) =:= ttr.tpe => m }
        if (constructors.isEmpty) report.throwError(s"Unable to find public constructor for $prefix(${ttr.show})")
        val annotatedConstructors = constructors.filter(_.symbol.annotations.exists(_.tpe.typeSymbol.fullName == "javax.inject.Inject"))
        val primaryConstructors = if (annotatedConstructors.nonEmpty) annotatedConstructors else constructors
        if (primaryConstructors.size > 1)
            report.throwError(s"More than one primary constructor was found for $prefix(${ttr.show})")
        primaryConstructors.head.symbol -> primaryConstructors.head.termParamss.map(tp => tp.isImplicit -> tp.params)
    }

    private def createResultFromConstructor(
        using q: Quotes)(ttr: q.reflect.TypeTree, constructor: q.reflect.Symbol, args: List[List[q.reflect.Term]]
    ): q.reflect.Term = {
        import q.reflect.*
        val ttrArgs = getTtrArguments(ttr)
        val typedConstructor = ttrArgs.map(_.values.map(t => TypeIdent(t.typeSymbol)).toList)
            .foldLeft[Term](Select(New(ttr), constructor))(TypeApply(_, _))
        typedConstructor.appliedToArgss(args)
    }

    private def createResultFromFunction(
        using q: Quotes)(f: q.reflect.Term, args: List[List[q.reflect.Term]]
    ): q.reflect.Term = {
        import q.reflect.*
        Select(f, f.tpe.typeSymbol.memberMethod("apply").head).appliedToArgss(args)
    }

    private def getFunctionArguments(using q: Quotes)(f: q.reflect.Tree): List[(Boolean, List[q.reflect.ValDef])] = {
        import q.reflect.*
        f match {
            case Inlined(_, _, Lambda(args, _)) => List(false -> args)
            case _ => report.throwError(s"Unsupported function type ${f.show}")
        }
    }

    private def getTtrArguments(using q: Quotes)(ttr: q.reflect.TypeTree): Option[Map[String, q.reflect.TypeRepr]] = {
        import q.reflect.*
        ttr.tpe match {
            case t: AppliedType => Option(ttr.tpe.typeSymbol.memberTypes.map(_.name).zip(t.args).toMap)
            case _ => None
        }
    }

    private def brew(
        using q: Quotes)(
        ttr: q.reflect.TypeTree,
        self: q.reflect.This,
        arguments: List[(Boolean, List[q.reflect.ValDef])],
        candidates: List[(q.reflect.Symbol, List[List[Nothing]], q.reflect.TypeRepr)],
        prefix: String)(
        resolveVacancy: (q.reflect.TypeTree, String) => q.reflect.Term,
        createResult: List[List[q.reflect.Term]] => q.reflect.Term
    ): Expr[?] = {
        import q.reflect.*
        val ttrArgs = getTtrArguments(ttr)
        val constructorArgs = arguments.map((impl, l) => l.map(p =>
            val ptpt = (ttrArgs, p.tpt.tpe) match {
                case (Some(args), TypeRef(_, name)) if args.contains(name) => TypeIdent(args(name).typeSymbol)
                case (_, _) => p.tpt
            }
            if (impl) ptpt.tpe.asType match {
                case '[tpe] => (Expr.summon[tpe] match {
                    case Some(arg) => arg
                    case _ => report.throwError(
                        s"Unable to resolve implicit instance for $prefix(${ttr.show}).${p.name}"
                    )
                }).asTerm
            } else {
                val parameterCandidates = candidates.filter(_._3 <:< ptpt.tpe)
                if (parameterCandidates.size > 1) report.throwError(
                    s"More than one injection candidate was found for $prefix(${ttr.show}).${p.name}"
                )
                parameterCandidates.headOption.fold(
                    resolveVacancy(ptpt, s"$prefix(${ttr.show}).${p.name}"))(
                    m => m._2.foldLeft[Term](Select(self, m._1))(Apply(_, _))
                )
            }
        ))
        createResult(constructorArgs.map(_.map(_.asExpr.asTerm))).asExpr
    }
}