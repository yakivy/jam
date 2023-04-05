package jam.cats.core

import cats.Monad
import jam.core.JamDsl

/**
 * Allows to brew objects that are lifted to F[_] using [[cats.Monad]].
 * Provides *F analogues of all methods in [[jam.core.JamCoreDsl]].
 */
trait JamCatsDsl extends JamDsl {
    inline def brewF[F[_]]: PartiallyAppliedBrewF[F] = PartiallyAppliedBrewF[F]
    class PartiallyAppliedBrewF[F[_]] {
        inline def apply[J](implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewFImpl[F, J]('config, 'F)
        }
    }

    inline def brewFromF[F[_]]: PartiallyAppliedBrewFromF[F] = PartiallyAppliedBrewFromF[F]
    class PartiallyAppliedBrewFromF[F[_]] {
        inline def apply[J](inline self: AnyRef)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewFromFImpl[F, J]('self)('config, 'F)
        }
    }

    inline def brewWithF[F[_]]: PartiallyAppliedBrewWithF[F] = PartiallyAppliedBrewWithF[F]
    class PartiallyAppliedBrewWithF[F[_]] {
        inline def apply[A1, J](inline f: A1 => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, J](inline f: (A1, A2) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, J](inline f: (A1, A2, A3) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFImpl[F, J]('f)('config, 'F)
        }
    }

    inline def brewWithFlatF[F[_]]: PartiallyAppliedBrewWithFlatF[F] = PartiallyAppliedBrewWithFlatF[F]
    class PartiallyAppliedBrewWithFlatF[F[_]] {
        inline def apply[A1, J](inline f: A1 => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, J](inline f: (A1, A2) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, J](inline f: (A1, A2, A3) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatFImpl[F, J]('f)('config, 'F)
        }
    }

    def brewFromWithF[F[_]]: PartiallyAppliedBrewFromWithF[F] = new PartiallyAppliedBrewFromWithF[F]
    class PartiallyAppliedBrewFromWithF[F[_]] {
        inline def apply[A1, J](inline self: AnyRef, inline f: A1 => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, J](inline self: AnyRef, inline f: (A1, A2) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, J](inline self: AnyRef, inline f: (A1, A2, A3) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, J](inline self: AnyRef, inline f: (A1, A2, A3, A4) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFImpl[F, J]('self, 'f)('config, 'F) }
    }
    
    def brewFromWithFlatF[F[_]]: PartiallyAppliedBrewFromWithFlatF[F] = new PartiallyAppliedBrewFromWithFlatF[F]
    class PartiallyAppliedBrewFromWithFlatF[F[_]] {
        inline def apply[A1, J](inline self: AnyRef, inline f: A1 => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, J](inline self: AnyRef, inline f: (A1, A2) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, J](inline self: AnyRef, inline f: (A1, A2, A3) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, J](inline self: AnyRef, inline f: (A1, A2, A3, A4) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatFImpl[F, J]('self, 'f)('config, 'F) }
    }

    inline def brewRecF[F[_]]: PartiallyAppliedBrewRecF[F] = PartiallyAppliedBrewRecF[F]
    class PartiallyAppliedBrewRecF[F[_]] {
        inline def apply[J](implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewRecFImpl[F, J]('config, 'F)
        }
    }

    inline def brewFromRecF[F[_]]: PartiallyAppliedBrewFromRecF[F] = PartiallyAppliedBrewFromRecF[F]
    class PartiallyAppliedBrewFromRecF[F[_]] {
        inline def apply[J](inline self: AnyRef)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewFromRecFImpl[F, J]('self)('config, 'F)
        }
    }

    inline def brewWithRecF[F[_]]: PartiallyAppliedBrewWithRecF[F] = PartiallyAppliedBrewWithRecF[F]
    class PartiallyAppliedBrewWithRecF[F[_]] {
        inline def apply[A1, J](inline f: A1 => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, J](inline f: (A1, A2) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, J](inline f: (A1, A2, A3) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithRecFImpl[F, J]('f)('config, 'F)
        }
    }

    inline def brewWithFlatRecF[F[_]]: PartiallyAppliedBrewWithFlatRecF[F] = PartiallyAppliedBrewWithFlatRecF[F]
    class PartiallyAppliedBrewWithFlatRecF[F[_]] {
        inline def apply[A1, J](inline f: A1 => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, J](inline f: (A1, A2) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, J](inline f: (A1, A2, A3) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${
            JamCatsMacro.brewWithFlatRecFImpl[F, J]('f)('config, 'F)
        }
    }

    def brewFromWithRecF[F[_]]: PartiallyAppliedBrewFromWithRecF[F] = new PartiallyAppliedBrewFromWithRecF[F]
    class PartiallyAppliedBrewFromWithRecF[F[_]] {
        inline def apply[A1, J](inline self: AnyRef, inline f: A1 => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, J](inline self: AnyRef, inline f: (A1, A2) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, J](inline self: AnyRef, inline f: (A1, A2, A3) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, J](inline self: AnyRef, inline f: (A1, A2, A3, A4) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithRecFImpl[F, J]('self, 'f)('config, 'F) }
    }

    def brewFromWithFlatRecF[F[_]]: PartiallyAppliedBrewFromWithFlatRecF[F] = new PartiallyAppliedBrewFromWithFlatRecF[F]
    class PartiallyAppliedBrewFromWithFlatRecF[F[_]] {
        inline def apply[A1, J](inline self: AnyRef, inline f: A1 => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, J](inline self: AnyRef, inline f: (A1, A2) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, J](inline self: AnyRef, inline f: (A1, A2, A3) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, J](inline self: AnyRef, inline f: (A1, A2, A3, A4) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
        inline def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => F[J])(implicit inline config: JamConfig, F: Monad[F]): F[J] = ${ JamCatsMacro.brewFromWithFlatRecFImpl[F, J]('self, 'f)('config, 'F) }
    }
}
