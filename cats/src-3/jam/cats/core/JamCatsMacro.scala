package jam.cats.core

import cats.Monad
import jam.core.*
import jam.core.JamCoreMacro.*
import scala.quoted.*

object JamCatsMacro {
    def brewFImpl[F[_]: Type, J: Type](using q: Quotes)(config: Expr[JamDsl#JamConfig], F: Expr[Monad[F]]): Expr[F[J]] =
        brewFromFImpl[F, J](findSelf.asExprOf[AnyRef])(config, F)

    private def liftFunctionToF[F[_]: Type, A: Type](using q: Quotes)(
        f: List[List[(q.reflect.TypeRepr, q.reflect.Term)]] => q.reflect.Term
    )(F: Expr[Monad[F]])(
        argss: List[List[(q.reflect.TypeRepr, q.reflect.Term)]]
    ): q.reflect.Term = {
        import q.reflect.*
        '{
            $F.map(cats.instances.list.catsStdInstancesForList.sequence[F, Any](
                ${ Expr.ofList(argss.view.flatMap(_.view.map(_._2.asExprOf[F[Any]])).toList) }
            )(using $F)){ pargss =>
                val indexedArgss = pargss.toIndexedSeq
                ${
                    f(argss.foldLeft((0, List.empty[List[(TypeRepr, Term)]])){ case ((c, r), args) =>
                        val liftedArgs = args.zipWithIndex.map { case ((tpe, _), i) =>
                            tpe.asType match { case '[t] =>
                                tpe -> '{ indexedArgss(${Expr(c + i)}).asInstanceOf[t] }.asTerm
                            }
                        }
                        (c + args.size, liftedArgs :: r)
                    }._2.reverse).asExprOf[A]
                }
            }
        }.asTerm
    }

    private def liftFunctionToFAndFlatten[F[_] : Type, A: Type](using q: Quotes)(
        f: List[List[(q.reflect.TypeRepr, q.reflect.Term)]] => q.reflect.Term
    )(F: Expr[Monad[F]])(
        argss: List[List[(q.reflect.TypeRepr, q.reflect.Term)]]
    ): q.reflect.Term = {
        import q.reflect.*
        val mappedTerm = liftFunctionToF[F, F[A]](f)(F)(argss)
        '{ $F.flatten(${ mappedTerm.asExprOf[F[F[A]]] }) }.asTerm
    }

    def brewFromFImpl[F[_]: Type, J: Type](using q: Quotes)(self: Expr[AnyRef])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = {
        import q.reflect.*
        val (ftpe, jtpe) = (TypeRepr.of[F], TypeRepr.of[J])
        val constructor = getConstructor(jtpe, prefix = "")
        val candidates = findCandidates(self.asTerm)
        brew(jtpe, Option(ftpe), self.asTerm, constructor._2, candidates, prefix = "")(
            abortOnVacancy,
            liftFunctionToF[F, J](createResultFromConstructor(jtpe, constructor._1, _))(F),
            Option(pure(F)),
        ).asExprOf[F[J]]
    }

    def brewWithFImpl[F[_]: Type, J: Type](using q: Quotes)(f: Expr[?])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = brewFromWithFImpl(findSelf.asExprOf[AnyRef], f)(config, F)

    def brewFromWithFImpl[F[_]: Type, J: Type](using q: Quotes)(self: Expr[AnyRef], f: Expr[?])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = {
        import q.reflect.*
        val (ftpe, jtpe) = (TypeRepr.of[F], TypeRepr.of[J])
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self.asTerm)
        brew(jtpe, Option(ftpe), self.asTerm, constructor, candidates, prefix = "")(
            abortOnVacancy,
            liftFunctionToF[F, J](createResultFromFunction(f.asTerm, _))(F),
            Option(pure(F)),
        ).asExprOf[F[J]]
    }

    def brewWithFlatFImpl[F[_] : Type, J: Type](using q: Quotes)(f: Expr[?])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = brewFromWithFlatFImpl(findSelf.asExprOf[AnyRef], f)(config, F)

    def brewFromWithFlatFImpl[F[_] : Type, J: Type](using q: Quotes)(self: Expr[AnyRef], f: Expr[?])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = {
        import q.reflect.*
        val (ftpe, jtpe) = (TypeRepr.of[F], TypeRepr.of[J])
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self.asTerm)
        brew(jtpe, Option(ftpe), self.asTerm, constructor, candidates, prefix = "")(
            abortOnVacancy,
            liftFunctionToFAndFlatten[F, J](createResultFromFunction(f.asTerm, _))(F),
            Option(pure(F)),
        ).asExprOf[F[J]]
    }

    def brewRecFImpl[F[_] : Type, J: Type](using q: Quotes)(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = brewFromRecFImpl[F, J](findSelf.asExprOf[AnyRef])(config, F)

    def brewFromRecFImpl[F[_] : Type, J: Type](using q: Quotes)(self: Expr[AnyRef])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = {
        import q.reflect.*
        val (ftpe, jtpe) = (TypeRepr.of[F], TypeRepr.of[J])
        val constructor = getConstructor(jtpe, prefix = "")
        val candidates = findCandidates(self.asTerm)
        val configR = parseConfig(config)
        brew(jtpe, Option(ftpe), self.asTerm, constructor._2, candidates, prefix = "")(
            createRecOnVacancyF(self, ftpe, configR, candidates)(F),
            liftFunctionToF[F, J](createResultFromConstructor(jtpe, constructor._1, _))(F),
            Option(pure(F)),
        ).asExprOf[F[J]]
    }

    def brewWithRecFImpl[F[_] : Type, J: Type](using q: Quotes)(f: Expr[?])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = brewFromWithRecFImpl(findSelf.asExprOf[AnyRef], f)(config, F)

    def brewFromWithRecFImpl[F[_] : Type, J: Type](using q: Quotes)(self: Expr[AnyRef], f: Expr[?])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = {
        import q.reflect.*
        val (ftpe, jtpe) = (TypeRepr.of[F], TypeRepr.of[J])
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self.asTerm)
        val configR = parseConfig(config)
        brew(jtpe, Option(ftpe), self.asTerm, constructor, candidates, prefix = "")(
            createRecOnVacancyF(self, ftpe, configR, candidates)(F),
            liftFunctionToF[F, J](createResultFromFunction(f.asTerm, _))(F),
            Option(pure(F)),
        ).asExprOf[F[J]]
    }

    def brewWithFlatRecFImpl[F[_] : Type, J: Type](using q: Quotes)(f: Expr[?])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = brewFromWithFlatRecFImpl(findSelf.asExprOf[AnyRef], f)(config, F)

    def brewFromWithFlatRecFImpl[F[_] : Type, J: Type](using q: Quotes)(self: Expr[AnyRef], f: Expr[?])(
        config: Expr[JamDsl#JamConfig],
        F: Expr[Monad[F]],
    ): Expr[F[J]] = {
        import q.reflect.*
        val (ftpe, jtpe) = (TypeRepr.of[F], TypeRepr.of[J])
        val constructor = getFunctionArguments(f.asTerm)
        val candidates = findCandidates(self.asTerm)
        val configR = parseConfig(config)
        brew(jtpe, Option(ftpe), self.asTerm, constructor, candidates, prefix = "")(
            createRecOnVacancyF(self, ftpe, configR, candidates)(F),
            liftFunctionToFAndFlatten[F, J](createResultFromFunction(f.asTerm, _))(F),
            Option(pure(F)),
        ).asExprOf[F[J]]
    }

    private def pure[F[_] : Type](using q: Quotes)(F: Expr[Monad[F]])(tree: q.reflect.Tree): q.reflect.Tree = {
        import q.reflect.*
        '{ $F.pure(${ tree.asExpr }) }.asTerm
    }

    private def createRecOnVacancyF[F[_] : Type, J : Type](using q: Quotes)(
        self: Expr[AnyRef],
        ftpe: q.reflect.TypeRepr,
        config: jam.JamConfig,
        candidates: List[(q.reflect.Symbol, List[List[Nothing]], q.reflect.TypeRepr)],
    )(F: Expr[Monad[F]])(jtpe: q.reflect.TypeRepr, prefix: String): q.reflect.Term = {
        import q.reflect.*
        def rec(jtpe: TypeRepr, prefix: String): Term = {
            validateBrewRecType(jtpe, config, prefix)
            val constructor = getConstructor(jtpe, prefix)
            jtpe.asType match { case '[t] =>
                brew(jtpe, Option(ftpe), self.asTerm, constructor._2, candidates, prefix)(
                    rec(_, _),
                    liftFunctionToF[F, t](createResultFromConstructor(jtpe, constructor._1, _))(F),
                    Option(pure(F)),
                )
            }
        }
        rec(jtpe, prefix)
    }
}