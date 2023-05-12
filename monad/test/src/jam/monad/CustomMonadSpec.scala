package jam.monad

import cats.effect.IO
import cats.effect.testing.scalatest.AsyncIOSpec
import cats.effect.unsafe.implicits._
import jam.CustomSpec
import org.scalatest.freespec.AsyncFreeSpec

abstract class CustomMonadSpec extends AsyncFreeSpec with CustomSpec with AsyncIOSpec {
    type Dep[A] = Reval[IO, A]
}
