package jam.cats.core

import cats.Monad
import jam.core._
import scala.reflect.macros.blackbox.Context

class JamCatsMacro(override val c: Context) extends JamCoreMacro(c) {
    def brewFImpl[F[_], J](config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]]
    ): c.Expr[F[J]] = {
        import c.universe._
        brewFromFImpl[F, J](q"this")(config, F)
    }

    private def liftFunctionToF[F[_]](
        ftpe: c.Type,
        atpe: c.Type,
    )(f: List[List[(c.Type, c.Tree)]] => c.Tree)(F: c.Expr[Monad[F]])(
        argss: List[List[(c.Type, c.Tree)]]
    ): c.Tree = {
        import c.universe._
        q"""
            $F.map(_root_.cats.instances.list.catsStdInstancesForList.sequence[$ftpe, Any](
                ${ argss.view.flatMap(_.view.map(_._2)).toList }
            )($F)){ argss =>
                val indexedArgss = argss.toIndexedSeq
                ${
                    f(argss.foldLeft((0, List.empty[List[(c.Type, c.Tree)]])){ case ((c, r), args) =>
                        val liftedArgs = args.zipWithIndex.map { case ((t, _), i) =>
                            t -> q"indexedArgss(${c + i}).asInstanceOf[$t]"
                        }
                        (c + args.size, liftedArgs :: r)
                    }._2.reverse)
                }
            }
        """
    }

    private def liftFunctionToFAndFlatten[F[_]](
        ftpe: c.Type,
        jtpe: c.Type,
    )(f: List[List[(c.Type, c.Tree)]] => c.Tree)(F: c.Expr[Monad[F]])(
        argss: List[List[(c.Type, c.Tree)]]
    ): c.Tree = {
        import c.universe._
        q"$F.flatten(${liftFunctionToF(ftpe, appliedType(ftpe.typeConstructor, jtpe))(f)(F)(argss)})"
    }

    private def liftFunctionToFAndFlattenIfNeeded[F[_]](
        ftpe: c.Type,
        jtpe: c.Type,
        flat: Boolean,
    )(f: List[List[(c.Type, c.Tree)]] => c.Tree)
        (F: c.Expr[Monad[F]])(
        argss: List[List[(c.Type, c.Tree)]]
    ): c.Tree = {
        if (flat) liftFunctionToFAndFlatten(ftpe, jtpe)(f)(F)(argss)
        else liftFunctionToF(ftpe, jtpe)(f)(F)(argss)
    }

    private def pure[F[_]](F: c.Expr[Monad[F]])(a: c.Tree): c.Tree = {
        import c.universe._
        q"$F.pure($a)"
    }

    def brewFromFImpl[F[_], J](self: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[F[J]] = {
        import c.universe._
        val candidates = findCandidates(self)
        val (jtpe, ftpe) = (JT.tpe.dealias, FT.tpe.dealias)
        val (constructorArgs, createFun, flat) = getConstructorArgumentsAndCreateFunction(jtpe, Option(ftpe), prefix = "")
        c.Expr(brew(self, jtpe, Option(ftpe), candidates, constructorArgs, prefix = "")(
            abortOnVacancy,
            liftFunctionToFAndFlattenIfNeeded(ftpe, jtpe, flat)(createFun)(F),
            Option(pure(F)),
        ))
    }

    def brewWithFImpl[F[_], J](f: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[J] = {
        import c.universe._
        brewFromWithFImpl[F, J](q"this", f)(config, F)
    }

    def brewFromWithFImpl[F[_], J](self: c.Tree, f: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[J] = {
        import c.universe._
        val candidates = findCandidates(self)
        val (jtpe, ftpe) = (JT.tpe.dealias, FT.tpe.dealias)
        c.Expr(brew(self, jtpe, Option(ftpe), candidates, getFunctionArguments(f), prefix = "")(
            abortOnVacancy,
            liftFunctionToF(ftpe, jtpe)(createResultFromFunction(f, _))(F),
            Option(pure(F)),
        ))
    }

    def brewWithFlatFImpl[F[_], J](f: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[F[J]] = {
        import c.universe._
        brewFromWithFlatFImpl(q"this", f)(config, F)
    }

    def brewFromWithFlatFImpl[F[_], J](self: c.Tree, f: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[F[J]] = {
        import c.universe._
        val candidates = findCandidates(self)
        val (jtpe, ftpe) = (JT.tpe.dealias, FT.tpe.dealias)
        c.Expr(brew(self, jtpe, Option(ftpe), candidates, getFunctionArguments(f), prefix = "")(
            abortOnVacancy,
            liftFunctionToFAndFlatten(ftpe, jtpe)(createResultFromFunction(f, _))(F),
            Option(pure(F)),
        ))
    }

    def brewRecFImpl[F[_], J](config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[F[J]] = {
        import c.universe._
        brewFromRecFImpl(q"this")(config, F)
    }

    def brewFromRecFImpl[F[_], J](self: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[F[J]] = {
        val candidates = findCandidates(self)
        val (jtpe, ftpe) = (JT.tpe.dealias, FT.tpe.dealias)
        val configR = parseConfig(config)
        val (constructorArgs, createFun, flat) = getConstructorArgumentsAndCreateFunction(jtpe, Option(ftpe), prefix = "")
        c.Expr(brew(self, jtpe, Option(ftpe), candidates, constructorArgs, prefix = "")(
            createRecOnVacancyF(self, ftpe, configR, candidates)(F),
            liftFunctionToFAndFlattenIfNeeded(ftpe, jtpe, flat)(createFun)(F),
            Option(pure(F)),
        ))
    }

    def brewWithRecFImpl[F[_], J](f: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[J] = {
        import c.universe._
        brewFromWithRecFImpl[F, J](q"this", f)(config, F)
    }

    def brewFromWithRecFImpl[F[_], J](self: c.Tree, f: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[J] = {
        val candidates = findCandidates(self)
        val (jtpe, ftpe) = (JT.tpe.dealias, FT.tpe.dealias)
        val configR = parseConfig(config)
        c.Expr(brew(self, jtpe, Option(ftpe), candidates, getFunctionArguments(f), prefix = "")(
            createRecOnVacancyF(self, ftpe, configR, candidates)(F),
            liftFunctionToF(ftpe, jtpe)(createResultFromFunction(f, _))(F),
            Option(pure(F)),
        ))
    }

    def brewWithFlatRecFImpl[F[_], J](f: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[F[J]] = {
        import c.universe._
        brewFromWithFlatRecFImpl(q"this", f)(config, F)
    }

    def brewFromWithFlatRecFImpl[F[_], J](self: c.Tree, f: c.Tree)(config: c.Tree, F: c.Expr[Monad[F]])(implicit
        JT: c.WeakTypeTag[J],
        FT: c.WeakTypeTag[F[_]],
    ): c.Expr[F[J]] = {
        import c.universe._
        val candidates = findCandidates(self)
        val (jtpe, ftpe) = (JT.tpe.dealias, FT.tpe.dealias)
        val configR = parseConfig(config)
        c.Expr(brew(self, jtpe, Option(ftpe), candidates, getFunctionArguments(f), prefix = "")(
            createRecOnVacancyF(self, ftpe, configR, candidates)(F),
            liftFunctionToFAndFlatten(ftpe, jtpe)(createResultFromFunction(f, _))(F),
            Option(pure(F)),
        ))
    }

    private def createRecOnVacancyF[F[_]](
        self: c.Tree,
        ftpe: c.Type,
        config: JamDsl#JamConfig,
        candidates: List[(c.universe.TermSymbol, c.universe.Type)],
    )(F: c.Expr[Monad[F]])(jtpe: c.Type, prefix: String): c.Tree = {
        def rec(jtpe: c.Type, prefix: String): c.Tree = {
            validateBrewRecType(jtpe, config, prefix)
            val (constructorArgs, createFun, flat) = getConstructorArgumentsAndCreateFunction(jtpe, Option(ftpe), prefix)
            brew(self, jtpe, Option(ftpe), candidates, constructorArgs, prefix)(
                rec(_, _),
                liftFunctionToFAndFlattenIfNeeded(ftpe, jtpe, flat)(createFun)(F),
                Option(pure(F)),
            )
        }
        rec(jtpe, prefix)
    }
}
