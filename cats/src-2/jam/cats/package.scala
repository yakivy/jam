package jam

import jam.cats.core.JamCatsDsl
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

package object cats extends JamCatsDsl {
    type JamCatsDsl = jam.cats.core.JamCatsDsl

    def defaultJamConfigImpl(c: blackbox.Context): c.Tree =
        c.universe.reify(new JamConfig(brewRecRegex = ".*")).tree

    implicit def defaultJamConfig: JamConfig = macro defaultJamConfigImpl
}
