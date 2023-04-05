package custom2

import jam.cats.core.JamCatsDsl
import jam.core.JamCoreDsl

object myjam extends JamCoreDsl with JamCatsDsl {
    implicit inline def myJamConfig: JamConfig =
        new JamConfig(brewRecRegex = "(?i).*brewable.*")
}
