package jam.tree

import jam.JamMacro

package object annotated {
    /**
     * Injects constructor arguments if they are provided in `this`,
     * recursively brews arguments that have a `jam.tree.annotated.brewable` class annotation
     * or throws an error
     */
    @deprecated("provide brewable instances manually instead")
    inline def brew[J]: J = ${ JamMacro.brewAnnotatedTreeImpl[J] }
}
