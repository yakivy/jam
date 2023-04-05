package jam.core

import scala.language.experimental.macros

trait JamCoreDsl extends JamDsl {
    /**
     * Injects constructor arguments if they are provided in `this`, otherwise throws an error
     */
    def brew[J](implicit config: JamConfig): J = macro JamCoreMacro.brewImpl[J]

    /**
     * Injects constructor arguments if they are provided in `self` argument, otherwise throws an error
     */
    def brewFrom[J](self: AnyRef)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromImpl[J]

    /**
     * Injects lambda arguments if they are provided in `this`, otherwise throws an error
     */
    def brewWith[A1, J](f: A1 => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, J](f: (A1, A2) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, J](f: (A1, A2, A3) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithImpl[J]

    /**
     * Injects lambda arguments if they are provided in `self` argument, otherwise throws an error
     */
    def brewFromWith[A1, J](self: AnyRef, f: A1 => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, J](self: AnyRef, f: (A1, A2) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, J](self: AnyRef, f: (A1, A2, A3) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, J](self: AnyRef, f: (A1, A2, A3, A4) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, J](self: AnyRef, f: (A1, A2, A3, A4, A5) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]
    def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithImpl[J]

    /**
     * Injects constructor arguments if they are provided in `this` or recursively brews them
     */
    def brewRec[J](implicit config: JamConfig): J = macro JamCoreMacro.brewRecImpl[J]

    /**
     * Injects constructor arguments if they are provided in `self` argument or recursively brews them
     */
    def brewFromRec[J](self: AnyRef)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromRecImpl[J]

    /**
     * Injects lambda arguments if they are provided in `this` or recursively brews them
     */
    def brewWithRec[A1, J](f: A1 => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, J](f: (A1, A2) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, J](f: (A1, A2, A3) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewWithRecImpl[J]

    /**
     * Injects lambda arguments if they are provided in `self` argument or recursively brews them
     */
    def brewFromWithRec[A1, J](self: AnyRef, f: A1 => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, J](self: AnyRef, f: (A1, A2) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, J](self: AnyRef, f: (A1, A2, A3) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, J](self: AnyRef, f: (A1, A2, A3, A4) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, J](self: AnyRef, f: (A1, A2, A3, A4, A5) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
    def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](self: AnyRef, f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit config: JamConfig): J = macro JamCoreMacro.brewFromWithRecImpl[J]
}
