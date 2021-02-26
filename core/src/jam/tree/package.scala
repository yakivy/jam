package jam

import scala.language.experimental.macros

package object tree {
    /**
     * Injects constructor arguments if they are provided in `this` or recursively brew them
     */
    def brew[J]: J = macro JamMacro.brewTreeImpl[J]
}
