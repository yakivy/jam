import scala.language.experimental.macros

package object jam {
    /**
     * Injects constructor arguments if they are provided in `this`, otherwise throws an error
     */
    def brew[J]: J = macro JamMacro.brewImpl[J]

    /**
     * Injects constructor arguments if they are provided in `self` argument, otherwise throws an error
     */
    def brewFrom[J](self: AnyRef): J = macro JamMacro.brewFromImpl[J]

    /**
     * Injects lambda arguments if they are provided in `this`, otherwise throws an error
     */
    def brewWith[A1, J](f: A1 => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, J](f: (A1, A2) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, J](f: (A1, A2, A3) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J): J = macro JamMacro.brewWithImpl[J]
    def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J): J = macro JamMacro.brewWithImpl[J]

    /**
     * Injects lambda arguments if they are provided in `self` argument, otherwise throws an error
     */
    def brewWithFrom[A1, J](f: A1 => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, J](f: (A1, A2) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, J](f: (A1, A2, A3) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]
    def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(self: AnyRef): J = macro JamMacro.brewWithFromImpl[J]

    /**
     * Injects constructor arguments if they are provided in `this` or recursively brews them
     */
    def brewRec[J]: J = macro JamMacro.brewRecImpl[J]

    /**
     * Injects constructor arguments if they are provided in `self` argument or recursively brews them
     */
    def brewFromRec[J](self: AnyRef): J = macro JamMacro.brewFromRecImpl[J]

    /**
     * Injects lambda arguments if they are provided in `this` or recursively brews them
     */
    def brewWithRec[A1, J](f: A1 => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, J](f: (A1, A2) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, J](f: (A1, A2, A3) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J): J = macro JamMacro.brewWithRecImpl[J]
    def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J): J = macro JamMacro.brewWithRecImpl[J]

    /**
     * Injects lambda arguments if they are provided in `self` argument or recursively brews them
     */
    def brewWithFromRec[A1, J](f: A1 => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, J](f: (A1, A2) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, J](f: (A1, A2, A3) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
    def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(self: AnyRef): J = macro JamMacro.brewWithFromRecImpl[J]
}
