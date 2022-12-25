import scala.language.experimental.macros
import scala.reflect.macros.blackbox

package object jam extends JamDsl {
    def defaultJamConfigImpl(c: blackbox.Context): c.Tree =
        c.universe.reify(new JamConfig(brewRecRegex = ".*")).tree

    implicit def defaultJamConfig: JamConfig = macro defaultJamConfigImpl
}
