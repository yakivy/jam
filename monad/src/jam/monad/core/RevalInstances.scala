package jam.monad.core

import cats._
import jam.monad.Reval
import jam.monad.core.RevalInstances._

object RevalInstances {
    private[core] class RevalMonad[F[_]] extends StackSafeMonad[Reval[F, *]] {
        override def pure[A](a: A): Reval[F, A] = Reval.pure(a)
        override def flatMap[A, B](fa: Reval[F, A])(f: A => Reval[F, B]): Reval[F, B] = fa.flatMap(f)
        override def map[A, B](fa: Reval[F, A])(f: A => B): Reval[F, B] = fa.map(f)
    }
    private[core] class RevalMonadError[F[_], E](implicit
        E: ApplicativeError[F, E]
    ) extends RevalMonad[F] with MonadError[Reval[F, *], E] {
        override def raiseError[A](e: E): Reval[F, A] = Reval.raiseError(e)
        override def handleErrorWith[A](fa: Reval[F, A])(f: E => Reval[F, A]): Reval[F, A] =
            fa.handleErrorWith(f)
        override def attempt[A](fa: Reval[F, A]): Reval[F, Either[E, A]] = fa.attempt[E]
    }
    private[core] class RevalMonoid[F[_], A: Monoid] extends Monoid[Reval[F, A]] {
        override def empty: Reval[F, A] = Reval.pure(Monoid[A].empty)
        override def combine(a: Reval[F, A], b: Reval[F, A]): Reval[F, A] = a.combine(b)
    }
}

trait RevalInstancesLp0 {
    implicit final def revalMonad[F[_]]: StackSafeMonad[Reval[F, *]] = new RevalMonad[F]
}

trait RevalInstances extends RevalInstancesLp0 {
    implicit final def revalMonoid[F[_], A: Monoid]: Monoid[Reval[F, A]] = new RevalMonoid[F, A]
    implicit final def revalMonadError[F[_], E](implicit
        E: ApplicativeError[F, E]
    ): MonadError[Reval[F, *], E] = new RevalMonadError[F, E]
}
