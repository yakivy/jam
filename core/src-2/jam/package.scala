import scala.language.experimental.macros

package object jam {
    /**
     * Injects constructor arguments if they are provided in `this`, otherwise throws an error
     */
    def brew[J]: J = macro JamMacro.brewImpl[J]

    /**
     * Injects lambda arguments if they are provided in `this`, otherwise throws an error
     */
    def brew[A1, J](f: A1 => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, J](f: (A1, A2) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, J](f: (A1, A2, A3) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, J](f: (A1, A2, A3, A4) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, J](f: (A1, A2, A3, A4, A5) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, J](f: (A1, A2, A3, A4, A5, A6) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, J](f: (A1, A2, A3, A4, A5, A6, A7) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, J](f: (A1, A2, A3, A4, A5, A6, A7, A8) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) => J): J = macro JamMacro.brewFImpl[J]
    def brew[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, J](f: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) => J): J = macro JamMacro.brewFImpl[J]
}
