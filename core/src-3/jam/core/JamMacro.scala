package jam.core

import jam.JamDsl
import jam.JamConfig
import scala.quoted.*

object JamMacro {
    def brewImpl[J](using q: Quotes)(using Type[J])(config: Expr[JamDsl#JamConfig]): Expr[J] = {
        brewFromImpl[J](findSelf.asExprOf[AnyRef])(config)
    }

    def brewFromImpl[J](using q: Quotes)(using Type[J])(self: Expr[AnyRef])(config: Expr[JamDsl#JamConfig]): Expr[J] = {
        import q.reflect.*
        val ttr = TypeTree.of[J]
        val constructor = getConstructor(ttr, "")
        val candidates = findCandidates(self.asTerm)
        brew(ttr, self.asTerm, constructor._2, candidates, "")(
            (ttr, prefix) => report.errorAndAbort(s"Unable to find instance for $prefix(${ttr.show})"),
            createResultFromConstructor(ttr, constructor._1, _)
        ).asExprOf
    }

    def brewWithImpl[J](using q: Quotes)(using Type[J])(f: Expr[?])(config: Expr[JamDsl#JamConfig]): Expr[J] =
        brewWithFromImpl(f)(findSelf.asExprOf[AnyRef])(config)

    def brewWithFromImpl[J](using q: Quotes)(using Type[J])(f: Expr[?])(self: Expr[AnyRef])(config: Expr[JamDsl#JamConfig]): Expr[J] = {
        import q.reflect.*
        val ttr = TypeTree.of[J]
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self.asTerm)
        brew(ttr, self.asTerm, constructor, candidates, "")(
            (ttr, prefix) => report.errorAndAbort(s"Unable to find instance for $prefix(${ttr.show})"),
            createResultFromFunction(f.asTerm, _)
        ).asExprOf
    }

    def brewRecImpl[J](using q: Quotes)(using Type[J])(config: Expr[JamDsl#JamConfig]): Expr[J] =
        brewFromRecImpl(findSelf.asExprOf[AnyRef])(config)

    def brewFromRecImpl[J](using q: Quotes)(using Type[J])(self: Expr[AnyRef])(config: Expr[JamDsl#JamConfig]): Expr[J] = {
        import q.reflect.*
        val ttr = TypeTree.of[J]
        val constructor = getConstructor(ttr, "")
        val candidates = findCandidates(self.asTerm)
        val configR = parseConfig(config)
        brew(ttr, self.asTerm, constructor._2, candidates, "")(
            (ttr, prefix) => {
                def rec(ttr: TypeTree, typePrefix: String): q.reflect.Term = {
                    validateBrewRecType(ttr, configR, prefix)
                    val constructor = getConstructor(ttr, prefix)
                    brew(
                        ttr, self.asTerm, constructor._2, candidates, typePrefix)(
                        rec(_, _), createResultFromConstructor(ttr, constructor._1, _)
                    ).asTerm
                }
                rec(ttr, prefix)
            },
            createResultFromConstructor(ttr, constructor._1, _)
        ).asExprOf
    }

    private def parseConfig(using q: Quotes)(config: Expr[JamDsl#JamConfig]): jam.JamConfig = {
        import q.reflect.*
        config.asTerm.underlyingArgument match {
            case Typed(Apply(_, List(Literal(StringConstant(brewRecRegex)))), _) =>
                new jam.JamConfig(brewRecRegex)
            case _ => report.errorAndAbort(
                "Unable to eval JamConfig at compile time. " +
                    "Provided value should be `inline def` and in `= JamConfig(...)` format"
            )
        }
    }

    private def validateBrewRecType(using q: Quotes)(ttr: q.reflect.TypeTree, config: jam.JamConfig, prefix: String): Unit = {
        import q.reflect.*
        if (!ttr.show.matches(config.brewRecRegex)) report.errorAndAbort(
            s"Recursive brewing for instance $prefix(${ttr.show}) is prohibited from config. " +
                s"${ttr.show} doesn't match ${config.brewRecRegex} regex."
        )
    }

    def brewWithRecImpl[J](using q: Quotes)(using Type[J])(f: Expr[?])(config: Expr[JamDsl#JamConfig]): Expr[J] =
        brewWithFromRecImpl(f)(findSelf.asExprOf[AnyRef])(config)

    def brewWithFromRecImpl[J](using q: Quotes)(using Type[J])(f: Expr[?])(self: Expr[AnyRef])(config: Expr[JamDsl#JamConfig]): Expr[J] = {
        import q.reflect.*
        val ttr = TypeTree.of[J]
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self.asTerm)
        val configR = parseConfig(config)
        brew(ttr, self.asTerm, constructor, candidates, "")(
            (ttr, prefix) => {
                def rec(ttr: TypeTree, typePrefix: String): q.reflect.Term = {
                    validateBrewRecType(ttr, configR, prefix)
                    val constructor = getConstructor(ttr, prefix)
                    brew(
                        ttr, self.asTerm, constructor._2, candidates, typePrefix)(
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
            case o if o.isClassDef => Option(o)
            case o => rec(o)
        }
        rec(Symbol.spliceOwner).map(This.apply)
            .getOrElse(report.errorAndAbort(s"Unable to access 'this'"))
    }

    private def findCandidates(
        using q: Quotes)(self: q.reflect.Term
    ): List[(q.reflect.Symbol, List[List[Nothing]], q.reflect.TypeRepr)] = {
        import q.reflect.*
        val tpeArgs = getTpeArguments(self.tpe)
        (self.tpe.typeSymbol.memberMethods ::: self.tpe.typeSymbol.memberFields)
            .filter(m => !m.fullName.startsWith("java.lang.Object") && !m.fullName.startsWith("scala.Any"))
            .map(_.tree).collect {
                case m: ValDef => (
                    m.symbol, Nil, resolveTpeRef(m.rhs.map(_.tpe).getOrElse(m.tpt.tpe))(tpeArgs)
                )
                case m: DefDef if m.termParamss.flatMap(_.params).isEmpty => (
                    m.symbol, m.termParamss.map(_ => Nil),
                    resolveTpeRef(m.rhs.map(_.tpe).getOrElse(m.returnTpt.tpe))(tpeArgs)
                )
            }
            .filter(m => !(m._3 =:= TypeRepr.of[Nothing]) && !(m._3 =:= TypeRepr.of[Null]))
    }

    private def resolveTpeRef(
        using q: Quotes)(tpe: q.reflect.TypeRepr)(args: Option[Map[String, q.reflect.TypeRepr]]
    ): q.reflect.TypeRepr = (args, tpe) match {
        case (Some(args), q.reflect.TypeRef(_, name)) if args.contains(name) => args(name)
        case (_, _) => tpe
    }

    private def getConstructor(
        using q: Quotes)(ttr: q.reflect.TypeTree, prefix: String
    ): (q.reflect.Symbol, List[(Boolean, List[q.reflect.ValDef])]) = {
        import q.reflect.*
        val tptArgs = getTpeArguments(ttr.tpe)
        val constructors = ttr.tpe.typeSymbol.declarations
            .filter(m => m.isClassConstructor).map(_.tree)
            .collect { case m: DefDef if tptArgs.fold(
                m.returnTpt.tpe)(
                args => m.returnTpt.tpe.asInstanceOf[AppliedType].tycon.appliedTo(args.values.toList)
            ) =:= ttr.tpe => m }
        if (constructors.isEmpty) report.errorAndAbort(s"Unable to find public constructor for $prefix(${ttr.show})")
        val annotatedConstructors = constructors.filter(_.symbol.annotations.exists(_.tpe.typeSymbol.fullName == "javax.inject.Inject"))
        val primaryConstructors = if (annotatedConstructors.nonEmpty) annotatedConstructors else constructors
        if (primaryConstructors.size > 1)
            report.errorAndAbort(s"More than one primary constructor was found for $prefix(${ttr.show})")
        primaryConstructors.head.symbol -> primaryConstructors.head.termParamss.map(tp => tp.isImplicit -> tp.params)
    }

    private def createResultFromConstructor(
        using q: Quotes)(ttr: q.reflect.TypeTree, constructor: q.reflect.Symbol, args: List[List[q.reflect.Term]]
    ): q.reflect.Term = {
        import q.reflect.*
        val ttrArgs = getTpeArguments(ttr.tpe)
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
        Option(f)
            .collect { case f: Term => f.underlyingArgument }
            .collect { case Lambda(args, _) => List(false -> args) }
            .getOrElse(report.errorAndAbort(s"Unsupported function type ${f.show}"))
    }

    private def getTpeArguments(using q: Quotes)(tpe: q.reflect.TypeRepr): Option[Map[String, q.reflect.TypeRepr]] = {
        import q.reflect.*
        tpe match {
            case t: AppliedType => Option(tpe.typeSymbol.memberTypes.map(_.name).zip(t.args).toMap)
            case _ => None
        }
    }

    private def brew(
        using q: Quotes)(
        ttr: q.reflect.TypeTree,
        self: q.reflect.Term,
        arguments: List[(Boolean, List[q.reflect.ValDef])],
        candidates: List[(q.reflect.Symbol, List[List[Nothing]], q.reflect.TypeRepr)],
        prefix: String)(
        resolveVacancy: (q.reflect.TypeTree, String) => q.reflect.Term,
        createResult: List[List[q.reflect.Term]] => q.reflect.Term
    ): Expr[?] = {
        import q.reflect.*
        val tptArgs = getTpeArguments(ttr.tpe)
        val constructorArgs = arguments.map((impl, l) => l.map(p =>
            val ptpt = TypeIdent(resolveTpeRef(p.tpt.tpe)(tptArgs).typeSymbol)
            if (impl) ptpt.tpe.asType match {
                case '[tpe] => (Expr.summon[tpe] match {
                    case Some(arg) => arg
                    case _ => report.errorAndAbort(
                        s"Unable to resolve implicit instance for $prefix(${ttr.show}).${p.name}"
                    )
                }).asTerm
            } else {
                val parameterCandidates = candidates.filter(_._3 <:< ptpt.tpe)
                if (parameterCandidates.size > 1) report.errorAndAbort(
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