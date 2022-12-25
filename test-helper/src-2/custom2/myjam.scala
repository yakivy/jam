package custom2

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object myjam extends jam.JamDsl {
    def myJamConfigImpl(c: blackbox.Context): c.Tree =
        c.universe.reify(new JamConfig(brewRecRegex = "(?i).*brewable.*")).tree

    implicit def myJamConfig: JamConfig = macro myJamConfigImpl
}
