package jam.cats.core

import cats.Monad
import jam.core.JamDsl
import scala.language.experimental.macros

/**
 * Allows to brew objects that are lifted to F[_] using [[cats.Monad]].
 * Provides *F analogues of all methods in [[jam.core.JamCoreDsl]].
 */
trait JamCatsDsl extends JamDsl {
    def brewF[F[_]]: PartiallyAppliedBrewF[F] = new PartiallyAppliedBrewF[F]
    class PartiallyAppliedBrewF[F[_]] {
       def apply[J](implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFImpl[F, J]
    }

    def brewFromF[F[_]]: PartiallyAppliedBrewFromF[F] = new PartiallyAppliedBrewFromF[F]
    class PartiallyAppliedBrewFromF[F[_]] {
        def apply[J](self: AnyRef)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromFImpl[F, J]
    }

    def brewWithF[F[_]]: PartiallyAppliedBrewWithF[F] = new PartiallyAppliedBrewWithF[F]
    class PartiallyAppliedBrewWithF[F[_]] {
        def apply[A1, J](f: A1 => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, J](f: (A1, A2) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, J](f: (A1, A2, A3) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFImpl[F, J]
    }

    def brewWithFlatF[F[_]]: PartiallyAppliedBrewWithFlatF[F] = new PartiallyAppliedBrewWithFlatF[F]
    class PartiallyAppliedBrewWithFlatF[F[_]] {
        def apply[A1, J](f: A1 => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, J](f: (A1, A2) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, J](f: (A1, A2, A3) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatFImpl[F, J]
    }

    def brewFromWithF[F[_]]: PartiallyAppliedBrewFromWithF[F] = new PartiallyAppliedBrewFromWithF[F]
    class PartiallyAppliedBrewFromWithF[F[_]] {
        def apply[A1, J](self: AnyRef, f: A1 => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, J](self: AnyRef, f: (A1, A2) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, J](self: AnyRef, f: (A1, A2, A3) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, J](self: AnyRef, f: (A1, A2, A3, A4) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, J](self: AnyRef, f: (A1, A2, A3, A4, A5) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFImpl[F, J]
    }

    def brewFromWithFlatF[F[_]]: PartiallyAppliedBrewFromWithFlatF[F] = new PartiallyAppliedBrewFromWithFlatF[F]
    class PartiallyAppliedBrewFromWithFlatF[F[_]] {
        def apply[A1, J](self: AnyRef, f: A1 => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, J](self: AnyRef, f: (A1, A2) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, J](self: AnyRef, f: (A1, A2, A3) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, J](self: AnyRef, f: (A1, A2, A3, A4) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, J](self: AnyRef, f: (A1, A2, A3, A4, A5) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatFImpl[F, J]
    }

    def brewRecF[F[_]]: PartiallyAppliedBrewRecF[F] = new PartiallyAppliedBrewRecF[F]
    class PartiallyAppliedBrewRecF[F[_]] {
       def apply[J](implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewRecFImpl[F, J]
    }

    def brewFromRecF[F[_]]: PartiallyAppliedBrewFromRecF[F] = new PartiallyAppliedBrewFromRecF[F]
    class PartiallyAppliedBrewFromRecF[F[_]] {
        def apply[J](self: AnyRef)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromRecFImpl[F, J]
    }

    def brewWithRecF[F[_]]: PartiallyAppliedBrewWithRecF[F] = new PartiallyAppliedBrewWithRecF[F]
    class PartiallyAppliedBrewWithRecF[F[_]] {
        def apply[A1, J](f: A1 => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, J](f: (A1, A2) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, J](f: (A1, A2, A3) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithRecFImpl[F, J]
    }

    def brewWithFlatRecF[F[_]]: PartiallyAppliedBrewWithFlatRecF[F] = new PartiallyAppliedBrewWithFlatRecF[F]
    class PartiallyAppliedBrewWithFlatRecF[F[_]] {
        def apply[A1, J](f: A1 => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, J](f: (A1, A2) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, J](f: (A1, A2, A3) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewWithFlatRecFImpl[F, J]
    }

    def brewFromWithRecF[F[_]]: PartiallyAppliedBrewFromWithRecF[F] = new PartiallyAppliedBrewFromWithRecF[F]
    class PartiallyAppliedBrewFromWithRecF[F[_]] {
        def apply[A1, J](self: AnyRef, f: A1 => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, J](self: AnyRef, f: (A1, A2) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, J](self: AnyRef, f: (A1, A2, A3) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, J](self: AnyRef, f: (A1, A2, A3, A4) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, J](self: AnyRef, f: (A1, A2, A3, A4, A5) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithRecFImpl[F, J]
    }

    def brewFromWithFlatRecF[F[_]]: PartiallyAppliedBrewFromWithFlatRecF[F] = new PartiallyAppliedBrewFromWithFlatRecF[F]
    class PartiallyAppliedBrewFromWithFlatRecF[F[_]] {
        def apply[A1, J](self: AnyRef, f: A1 => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, J](self: AnyRef, f: (A1, A2) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, J](self: AnyRef, f: (A1, A2, A3) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, J](self: AnyRef, f: (A1, A2, A3, A4) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, J](self: AnyRef, f: (A1, A2, A3, A4, A5) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
        def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => F[J])(implicit config: JamConfig, F: Monad[F]): F[J] =
            macro JamCatsMacro.brewFromWithFlatRecFImpl[F, J]
    }
}
