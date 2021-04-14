package jam.tree

import jam.JamMacro

package object annotated {
    /**
     * Injects constructor arguments if they are provided in `this`,
     * recursively brews arguments that have a `jam.tree.annotated.brewable` class annotation
     * or throws an error
     */
    inline def brew[J]: J = ${ JamMacro.brewAnnotatedTreeImpl[J] }
}
