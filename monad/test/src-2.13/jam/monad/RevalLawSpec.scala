package jam.monad

import cats._
import cats.effect.Resource
import cats.effect.kernel.testkit.pure.PureConc
import cats.effect.kernel.testkit.TestInstances
import cats.laws.discipline._
import cats.kernel.laws.discipline.MonoidTests
import cats.laws.discipline.SemigroupalTests.Isomorphisms
import org.scalacheck.Arbitrary
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers
import org.typelevel.discipline.scalatest.WordSpecDiscipline
import cats.laws.discipline.arbitrary._
import cats.effect.kernel.testkit.pure._
import cats.effect.kernel.testkit.PureConcGenerators._
import jam.monad.core.RevalKeyGen

class RevalLawSpec extends AnyWordSpec with WordSpecDiscipline with Checkers with TestInstances {
    implicit val eqThrow: Eq[Throwable] = Eq.allEqual

    implicit def isomorphismsForReval[F[_]](implicit
        F: Invariant[Reval[F, *]]
    ): Isomorphisms[Reval[F, *]] = Isomorphisms.invariant[Reval[F, *]]

    implicit def arbitraryForReval[A](implicit
        A: Arbitrary[Resource[PureConc[Throwable, *], A]],
    ): Arbitrary[Reval[PureConc[Throwable, *], A]] =
        Arbitrary(A.arbitrary.map(r => Reval.resourceAlways(r)))

    implicit def revalEq[A](implicit
        A: Eq[Resource[PureConc[Throwable, *], A]],
    ): Eq[Reval[PureConc[Throwable, *], A]] =
        Eq.by[Reval[PureConc[Throwable, *], A], Resource[PureConc[Throwable, *], A]](_.asResource)

    checkAll(
        "Reval[PureConc[Throwable, *], *].MonadErrorTests",
        MonadErrorTests[Reval[PureConc[Throwable, *], *], Throwable](Reval.revalMonadError)
            .monadError[Int, Int, Int]
    )
    checkAll(
        "Reval[PureConc[Throwable, *], *].MonadTests",
        MonadTests[Reval[PureConc[Throwable, *], *]](Reval.revalMonad)
            .monad[Int, Int, Int]
    )
    checkAll(
        "Reval[PureConc[Throwable, *], *].MonoidTests",
        MonoidTests[Reval[PureConc[Throwable, *], Int]].monoid
    )
}
