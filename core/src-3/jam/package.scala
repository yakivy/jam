import jam.core.JamCoreDsl

package jam {
    //emulation of package object as it was deprecated
    private object internal extends JamCoreDsl {
        implicit inline def defaultJamConfig: this.JamConfig =
            new this.JamConfig(brewRecRegex = ".*")
    }

    export internal._
}
