package jam.monad.core

trait RevalKeyGenTypeBinCompat {
    implicit inline def positionKeyGen[A]: RevalKeyGen[A] = ${
        JamMonadMacro.positionKeyGenImpl[A]
    }
}
