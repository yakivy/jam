package object jam {
    /**
     * Injects constructor arguments if they are provided in `this`, otherwise throws an error
     */
    inline def brew[J]: J = ${ JamMacro.brewImpl[J] }
}
