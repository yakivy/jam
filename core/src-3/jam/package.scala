package jam {
    //emulation of package object as it was deprecated
    object internal extends JamDsl {
        implicit inline def defaultJamConfig: this.JamConfig =
            new this.JamConfig(brewRecRegex = ".*")
    }
    export internal._
}
