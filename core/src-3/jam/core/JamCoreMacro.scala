package jam.core

import scala.quoted.*

object JamCoreMacro {
    def brewImpl[J: Type](using q: Quotes)(config: Expr[JamDsl#JamConfig]): Expr[J] = {
        brewFromImpl[J](findSelf.asExprOf[AnyRef])(config)
    }

    def brewFromImpl[J: Type](using q: Quotes)(self: Expr[AnyRef])(config: Expr[JamDsl#JamConfig]): Expr[J] = {
        import q.reflect.*
        val tpe = TypeRepr.of[J]
        val constructor = getConstructor(tpe, prefix = "")
        val candidates = findCandidates(self.asTerm)
        brew(tpe, ftpe = None, self.asTerm, constructor._2, candidates, prefix = "")(
            abortOnVacancy,
            createResultFromConstructor(tpe, constructor._1, _),
            pure = None,
        ).asExprOf
    }

    def brewWithImpl[J: Type](using q: Quotes)(f: Expr[?])(config: Expr[JamDsl#JamConfig]): Expr[J] =
        brewFromWithImpl(f)(findSelf.asExprOf[AnyRef])(config)

    def brewFromWithImpl[J: Type](using q: Quotes)(f: Expr[?])(self: Expr[AnyRef])(config: Expr[JamDsl#JamConfig]): Expr[J] = {
        import q.reflect.*
        val tpe = TypeRepr.of[J]
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self.asTerm)
        brew(tpe, ftpe = None, self.asTerm, constructor, candidates, prefix = "")(
            abortOnVacancy,
            createResultFromFunction(f.asTerm, _),
            pure = None,
        ).asExprOf
    }

    def brewRecImpl[J: Type](using q: Quotes)(config: Expr[JamDsl#JamConfig]): Expr[J] =
        brewFromRecImpl(findSelf.asExprOf[AnyRef])(config)

    def brewFromRecImpl[J: Type](using q: Quotes)(self: Expr[AnyRef])(config: Expr[JamDsl#JamConfig]): Expr[J] = {
        import q.reflect.*
        val tpe = TypeRepr.of[J]
        val constructor = getConstructor(tpe, prefix = "")
        val candidates = findCandidates(self.asTerm)
        val configR = parseConfig(config)
        brew(tpe, ftpe = None, self.asTerm, constructor._2, candidates, prefix = "")(
            createRecOnVacancy(self, configR, candidates),
            createResultFromConstructor(tpe, constructor._1, _),
            pure = None,
        ).asExprOf
    }

    private[jam] def parseConfig(using q: Quotes)(config: Expr[JamDsl#JamConfig]): jam.JamConfig = {
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

    private[jam] def validateBrewRecType(using q: Quotes)(
        tpe: q.reflect.TypeRepr,
        config: jam.JamConfig,
        prefix: String,
    ): Unit = {
        import q.reflect.*
        if (!tpe.show.matches(config.brewRecRegex)) report.errorAndAbort(
            s"Recursive brewing for instance $prefix(${tpe.show}) is prohibited from config. " +
                s"${tpe.show} doesn't match ${config.brewRecRegex} regex."
        )
    }

    def brewWithRecImpl[J: Type](using q: Quotes)(f: Expr[?])(config: Expr[JamDsl#JamConfig]): Expr[J] =
        brewFromWithRecImpl(findSelf.asExprOf[AnyRef], f)(config)

    def brewFromWithRecImpl[J: Type](using q: Quotes)(self: Expr[AnyRef], f: Expr[?])(
        config: Expr[JamDsl#JamConfig],
    ): Expr[J] = {
        import q.reflect.*
        val tpe = TypeRepr.of[J]
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self.asTerm)
        val configR = parseConfig(config)
        brew(tpe, ftpe = None, self.asTerm, constructor, candidates, prefix = "")(
            createRecOnVacancy(self, configR, candidates),
            createResultFromFunction(f.asTerm, _),
            pure = None,
        ).asExprOf
    }

    private[jam] def findSelf(using q: Quotes): q.reflect.This = {
        import q.reflect.*
        def rec(s: Symbol): Option[Symbol] = s.maybeOwner match {
            case o if o.isNoSymbol => None
            case o if o.isClassDef => Option(o)
            case o => rec(o)
        }
        rec(Symbol.spliceOwner).map(This.apply)
            .getOrElse(report.errorAndAbort(s"Unable to access 'this'"))
    }

    private[jam] def findCandidates(
        using q: Quotes)(self: q.reflect.Term
    ): List[(q.reflect.Symbol, List[List[Nothing]], q.reflect.TypeRepr)] = {
        import q.reflect.*
        val tpeArgs = getTpeArguments(self.tpe)
        (self.tpe.typeSymbol.methodMembers ::: self.tpe.typeSymbol.fieldMembers)
            .view
            .filter(m => !m.fullName.startsWith("java.lang.Object.") && !m.fullName.startsWith("scala.Any."))
            .filter(!_.isClassConstructor)
            .map(_.tree)
            .collect {
                case m: ValDef => (
                    m.symbol, Nil, resolveTpeRef(m.tpt.tpe)(tpeArgs)
                )
                case m: DefDef if m.termParamss.flatMap(_.params).isEmpty => (
                    m.symbol, m.termParamss.map(_ => Nil),
                    resolveTpeRef(m.returnTpt.tpe)(tpeArgs)
                )
            }
            .filter(m => !(m._3 =:= TypeRepr.of[Nothing]) && !(m._3 =:= TypeRepr.of[Null]))
            .toList
    }

    private def resolveTpeRef(
        using q: Quotes)(tpe: q.reflect.TypeRepr)(args: Option[Map[String, q.reflect.TypeRepr]]
    ): q.reflect.TypeRepr = (args, tpe) match {
        case (Some(args), q.reflect.TypeRef(_, name)) if args.contains(name) => args(name)
        case (_, _) => tpe
    }

    private[jam] def getConstructor(using q: Quotes)(
        tpe: q.reflect.TypeRepr,
        prefix: String,
    ): (q.reflect.Symbol, List[(Boolean, List[q.reflect.ValDef])]) = {
        import q.reflect.*
        val tptArgs = getTpeArguments(tpe)
        val constructors = tpe.typeSymbol.declarations
            .filter(m => m.isClassConstructor && !m.flags.is(Flags.Private) && !m.flags.is(Flags.Protected))
            .map(_.tree)
            .collect { case m: DefDef if tptArgs.fold(m.returnTpt.tpe)(args =>
                m.returnTpt.tpe.asInstanceOf[AppliedType].tycon.appliedTo(args.values.toList)
            ) =:= tpe => m }
        if (constructors.isEmpty) report.errorAndAbort(s"Unable to find public constructor for $prefix(${tpe.show})")
        val annotatedConstructors = constructors.filter(_.symbol.annotations.exists(_.tpe.typeSymbol.fullName == "javax.inject.Inject"))
        val primaryConstructors = if (annotatedConstructors.nonEmpty) annotatedConstructors else constructors
        if (primaryConstructors.size > 1)
            report.errorAndAbort(s"More than one primary constructor was found for $prefix(${tpe.show})")
        primaryConstructors.head.symbol -> primaryConstructors.head.termParamss.map(tp => tp.isImplicit -> tp.params)
    }

    private[jam] def createResultFromConstructor(using q: Quotes)(
        tpe: q.reflect.TypeRepr,
        constructor: q.reflect.Symbol,
        args: List[List[(q.reflect.TypeRepr, q.reflect.Term)]],
    ): q.reflect.Term = {
        import q.reflect.*
        val tpeArgs = getTpeArguments(tpe)
        val typedConstructor = tpeArgs.map(_.values.map(t => TypeIdent(t.typeSymbol)).toList)
            .foldLeft[Term](Select(New(TypeIdent(tpe.dealias.typeSymbol)), constructor))(TypeApply(_, _))
        typedConstructor.appliedToArgss(args.map(_.map(_._2)))
    }

    private[jam] def createResultFromFunction(
        using q: Quotes)(f: q.reflect.Term, args: List[List[(q.reflect.TypeRepr, q.reflect.Term)]]
    ): q.reflect.Term = {
        import q.reflect.*
        Select(f, f.tpe.typeSymbol.methodMember("apply").head).appliedToArgss(args.map(_.map(_._2)))
    }

    private[jam] def getFunctionArguments(using q: Quotes)(f: q.reflect.Tree): List[(Boolean, List[q.reflect.ValDef])] = {
        import q.reflect.*
        Option(f)
            .collect { case f: Term => f.underlyingArgument }
            .collect { case Lambda(args, _) => List(false -> args) }
            .getOrElse(report.errorAndAbort(s"Unsupported function type ${f.show}"))
    }

    private def getTpeArguments(using q: Quotes)(tpe: q.reflect.TypeRepr): Option[Map[String, q.reflect.TypeRepr]] = {
        import q.reflect.*
        tpe match {
            case t: AppliedType => Option(tpe.typeSymbol.typeMembers.map(_.name).zip(t.args).toMap)
            case _ => None
        }
    }

    private[jam] def abortOnVacancy(using q: Quotes)(tpe: q.reflect.TypeRepr, prefix: String): q.reflect.Term = {
        import q.reflect.*
        report.errorAndAbort(s"Unable to find instance for $prefix(${tpe.show})")
    }

    private def createRecOnVacancy(using q: Quotes)(
        self: Expr[AnyRef],
        config: jam.JamConfig,
        candidates: List[(q.reflect.Symbol, List[List[Nothing]], q.reflect.TypeRepr)],
    )(tpe: q.reflect.TypeRepr, prefix: String): q.reflect.Term = {
        import q.reflect.*
        def rec(tpe: TypeRepr, prefix: String): Term = {
            validateBrewRecType(tpe, config, prefix)
            val constructor = getConstructor(tpe, prefix)
            brew(tpe, ftpe = None, self.asTerm, constructor._2, candidates, prefix)(
                rec(_, _),
                createResultFromConstructor(tpe, constructor._1, _),
                pure = None,
            )
        }
        rec(tpe, prefix)
    }

    private[jam] def brew(
        using q: Quotes
    )(
        atpe: q.reflect.TypeRepr,
        ftpe: Option[q.reflect.TypeRepr],
        self: q.reflect.Term,
        arguments: List[(Boolean, List[q.reflect.ValDef])],
        candidates: List[(q.reflect.Symbol, List[List[Nothing]], q.reflect.TypeRepr)],
        prefix: String,
    )(
        resolveVacancy: (q.reflect.TypeRepr, String) => q.reflect.Term,
        createResult: List[List[(q.reflect.TypeRepr, q.reflect.Term)]] => q.reflect.Term,
        pure: Option[q.reflect.Tree => q.reflect.Tree],
    ): q.reflect.Term = {
        import q.reflect.*
        val tptArgs = getTpeArguments(atpe)
        val constructorArgs = arguments.map((impl, l) => l.map(p =>
            val ptpe = resolveTpeRef(Ref(p.symbol).tpe.widen)(tptArgs)
            val pftpe = ftpe.map(_.appliedTo(ptpe))
            ptpe -> (if (impl) ptpe.asType match {
                case '[tpe] => (Expr.summon[tpe] match {
                    case Some(arg) => arg
                    case _ => report.errorAndAbort(
                        s"Unable to resolve implicit instance for $prefix(${atpe.show}).${p.name}(${ptpe.show})"
                    )
                }).asTerm
            } else {
                val parameterCandidates = candidates.filter(c => c._3 <:< ptpe || pftpe.exists(c._3 <:< _))
                if (parameterCandidates.size > 1) report.errorAndAbort(
                    s"More than one injection candidate was found for $prefix(${atpe.show}).${p.name}(${ptpe.show}): " +
                        s"${parameterCandidates.map(c => s"${c._1.fullName}(${c._3.show})").sorted.mkString(", ")}"
                )
                parameterCandidates.headOption.fold(
                    resolveVacancy(ptpe, s"$prefix(${atpe.show}).${p.name}")
                ) { m =>
                    val arg = m._2.foldLeft[Term](Select(self, m._1))(Apply(_, _))
                    if (pftpe.forall(m._3 <:< _)) arg else pure.get(arg)
                }
            }).asExpr.asTerm
        ))
        createResult(constructorArgs)
    }
}