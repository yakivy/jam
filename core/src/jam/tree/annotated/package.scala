package jam.tree

import jam.JamMacro
import scala.language.experimental.macros

package object annotated {
    /**
     * Injects constructor arguments if they are provided in `this`,
     * recursively brews arguments that have a `jam.tree.annotated.brewable` class annotation
     * or throws an error
     */
    def brew[J]: J = macro JamMacro.brewAnnotatedTreeImpl[J]
}
