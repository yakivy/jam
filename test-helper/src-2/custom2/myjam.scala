package custom2

import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import jam.core.JamCoreDsl
import jam.cats.core.JamCatsDsl

object myjam extends JamCoreDsl with JamCatsDsl {
    def myJamConfigImpl(c: blackbox.Context): c.Tree =
        c.universe.reify(new JamConfig(brewRecRegex = "(?i).*brewable.*")).tree

    implicit def myJamConfig: JamConfig = macro myJamConfigImpl
}
