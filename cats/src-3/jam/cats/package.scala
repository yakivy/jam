package jam

import jam.cats.core.JamCatsDsl

package object cats {
    object internal extends JamCatsDsl {
        implicit inline def defaultJamConfig: this.JamConfig =
            new this.JamConfig(brewRecRegex = ".*")
    }
    
    export internal._
}
