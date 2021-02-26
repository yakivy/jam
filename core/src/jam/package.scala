import scala.language.experimental.macros

package object jam {
    /**
     * Injects constructor arguments if they are provided in `this`, otherwise throws an error
     */
    def brew[J]: J = macro JamMacro.brewImpl[J]
}
