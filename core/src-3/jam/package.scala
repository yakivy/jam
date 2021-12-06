package object jam {
    /**
     * Injects constructor arguments if they are provided in `this`, otherwise throws an error
     */
    inline def brew[J]: J = ${ JamMacro.brewImpl[J] }

    /**
     * Injects constructor arguments if they are provided in `self` argument, otherwise throws an error
     */
    inline def brewFrom[J](inline self: AnyRef): J = ${ JamMacro.brewFromImpl[J]('self) }

    /**
     * Injects lambda arguments if they are provided in `this`, otherwise throws an error
     */
    inline def brewWith[A1, J](inline f: A1 => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, J](inline f: (A1, A2) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, J](inline f: (A1, A2, A3) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J): J = ${ JamMacro.brewWithImpl[J]('f) }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J): J = ${ JamMacro.brewWithImpl[J]('f) }

    /**
     * Injects lambda arguments if they are provided in `self` argument, otherwise throws an error
     */
    inline def brewWithFrom[A1, J](inline f: A1 => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, J](inline f: (A1, A2) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, J](inline f: (A1, A2, A3) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }
    inline def brewWithFrom[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromImpl[J]('f)('self) }

    /**
     * Injects constructor arguments if they are provided in `this` or recursively brews them
     */
    inline def brewRec[J]: J = ${ JamMacro.brewRecImpl[J] }

    /**
     * Injects constructor arguments if they are provided in `self` argument or recursively brews them
     */
    inline def brewFromRec[J](inline self: AnyRef): J = ${ JamMacro.brewFromRecImpl[J]('self) }

    /**
     * Injects lambda arguments if they are provided in `this` or recursively brews them
     */
    inline def brewWithRec[A1, J](inline f: A1 => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, J](inline f: (A1, A2) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, J](inline f: (A1, A2, A3) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J): J = ${ JamMacro.brewWithRecImpl[J]('f) }

    /**
     * Injects lambda arguments if they are provided in `self` argument or recursively brews them
     */
    inline def brewWithFromRec[A1, J](inline f: A1 => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, J](inline f: (A1, A2) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, J](inline f: (A1, A2, A3) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
    inline def brewWithFromRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(inline self: AnyRef): J = ${ JamMacro.brewWithFromRecImpl[J]('f)('self) }
}
