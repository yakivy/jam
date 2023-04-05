package jam.core

trait JamCoreDsl extends JamDsl {
    /**
     * Injects constructor arguments if they are provided in `this`, otherwise throws an error
     */
    inline def brew[J](implicit inline config: JamConfig): J = ${ JamCoreMacro.brewImpl[J]('config) }

    /**
     * Injects constructor arguments if they are provided in `self` argument, otherwise throws an error
     */
    inline def brewFrom[J](inline self: AnyRef)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewFromImpl[J]('self)('config)
    }

    /**
     * Injects lambda arguments if they are provided in `this`, otherwise throws an error
     */
    inline def brewWith[A1, J](inline f: A1 => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, J](inline f: (A1, A2) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, J](inline f: (A1, A2, A3) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }
    inline def brewWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewWithImpl[J]('f)('config)
    }

    /**
     * Injects lambda arguments if they are provided in `self` argument, otherwise throws an error
     */
    inline def brewFromWith[A1, J](inline self: AnyRef, inline f: A1 => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, J](inline self: AnyRef, inline f: (A1, A2) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, J](inline self: AnyRef, inline f: (A1, A2, A3) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, J](inline self: AnyRef, inline f: (A1, A2, A3, A4) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }
    inline def brewFromWith[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithImpl[J]('f)('self)('config) }

    /**
     * Injects constructor arguments if they are provided in `this` or recursively brews them
     */
    inline def brewRec[J](implicit inline config: JamConfig): J = ${ JamCoreMacro.brewRecImpl[J]('config) }

    /**
     * Injects constructor arguments if they are provided in `self` argument or recursively brews them
     */
    inline def brewFromRec[J](inline self: AnyRef)(implicit inline config: JamConfig): J = ${
        JamCoreMacro.brewFromRecImpl[J]('self)('config)
    }

    /**
     * Injects lambda arguments if they are provided in `this` or recursively brews them
     */
    inline def brewWithRec[A1, J](inline f: A1 => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, J](inline f: (A1, A2) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, J](inline f: (A1, A2, A3) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, J](inline f: (A1, A2, A3, A4) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, J](inline f: (A1, A2, A3, A4, A5) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, J](inline f: (A1, A2, A3, A4, A5, A6) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, J](inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }
    inline def brewWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewWithRecImpl[J]('f)('config) }

    /**
     * Injects lambda arguments if they are provided in `self` argument or recursively brews them
     */
    inline def brewFromWithRec[A1, J](inline self: AnyRef, inline f: A1 => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, J](inline self: AnyRef, inline f: (A1, A2) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, J](inline self: AnyRef, inline f: (A1, A2, A3) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, J](inline self: AnyRef, inline f: (A1, A2, A3, A4) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
    inline def brewFromWithRec[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](inline self: AnyRef, inline f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J)(implicit inline config: JamConfig): J = ${ JamCoreMacro.brewFromWithRecImpl[J]('self, 'f)('config) }
}
