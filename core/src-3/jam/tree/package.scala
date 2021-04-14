package jam

package object tree {
    /**
     * Injects constructor arguments if they are provided in `this` or recursively brew them
     */
    inline def brew[J]: J = ${ JamMacro.brewTreeImpl[J] }
}
