package jam.monad.core

import scala.language.experimental.macros

trait RevalKeyGenTypeBinCompat {
    implicit def positionKeyGen[A]: RevalKeyGen[A] =
        macro JamMonadMacro.positionKeyGenImpl[A]
}
