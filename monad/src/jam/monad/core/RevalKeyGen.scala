package jam.monad.core

case class RevalKeyGen[A](key: Object)

object RevalKeyGen extends RevalKeyGenTypeBinCompat {
    def apply[A](implicit key: RevalKeyGen[A]): RevalKeyGen[A] = key
}
