import scala.language.experimental.macros

package object jam {
    def brew[J]: J = macro JamMacro.brewImpl[J]
}
