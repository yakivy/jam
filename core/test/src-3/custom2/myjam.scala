package custom2

import jam.JamDsl

object myjam extends JamDsl {
    implicit inline def myJamConfig: JamConfig =
        new JamConfig(brewRecRegex = "(?i).*brewable.*")
}
