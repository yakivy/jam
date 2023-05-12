package jam.monad

import cats._
import cats.effect._
import cats.implicits._
import jam.monad.core.RevalInstances
import jam.monad.core.RevalKeyGen

/**
 * `Reval` is a data structure which encodes the idea of allocating an object which has an
 * associated finalizer. Can be thought of as a mix of `cats.effect.Resource` and `cats.Eval`.
 * There are two allocation strategies:
 *   - `later`: allocates the object once when it is needed
 *   - `always`: allocates the object every time it is needed
 *
 * For example:
 * {{{
 * scala> var c = 0
 * scala> val a = Reval.thunkLater[IO, Int]{c += 1; c}
 * scala> a.replicateA(3).map(_.sum).usePure.unsafeRunSync() // List(1, 1, 1).sum
 * val res0: Int = 3
 * }}}
 * {{{
 * scala> var c = 0
 * scala> val a = Reval.thunkAlways[IO, Int]{c += 1; c}
 * scala> a.replicateA(3).map(_.sum).usePure.unsafeRunSync() // List(1, 2, 3).sum
 * val res0: Int = 6
 * }}}
 *
 * Common pitfalls:
 *   - `later` with multiple `use` calls will allocate later objects for each `use`
 * {{{
 * scala> var c = 0
 * scala> val a = Reval.thunkLater[IO, Int] { c += 1; c }
 * scala> (a.usePure, a.usePure).mapN(_ + _).unsafeRunSync() // 1 + 2
 * val res1: Int = 3
 * }}}
 */
sealed abstract class Reval[F[_], +A] {

    /**
     * Allocates an object and supplies it to the given function. The finalizer is called as
     * soon as the resulting `F[B]` is completed, whether normally or as a raised error.
     */
    def use[B](f: A => F[B])(implicit F: MonadCancel[F, Throwable]): F[B] = asResource.use(f)

    def usePure[B](implicit ev: A <:< B, F: MonadCancel[F, Throwable]): F[B] = use(F.pure(_))

    /**
     * Represents a reval as a `cats.effect.Resource`.
     */
    def asResource: Resource[F, A] = {
        def loop[C](
            reval: Reval[F, C],
            revalMap: Map[Object, Resource[F, Any]],
        ): Resource[F, (Resource[F, C], Map[Object, Resource[F, Any]])] = reval match {
            case self: Reval.Memoize[F, C] =>
                revalMap.get(self.key).fold(
                    for {
                        result <- loop(self.s, revalMap)
                        a <- result._1
                        r = Resource.pure[F, C](a)
                    } yield r -> (result._2 + (self.key -> r))
                )(r => (r.asInstanceOf[Resource[F, C]], revalMap).pure[Resource[F, *]])
            case self: Reval.Eval[F, C] => (self.r, revalMap).pure[Resource[F, *]]
            case self: Reval.FlatMap[F, Any, C] =>
                for {
                    aResult <- loop(self.s, revalMap)
                    a <- aResult._1
                    fb = self.fs(a)
                    bResult <- loop(fb, aResult._2)
                } yield bResult
        }
        loop(this, Map.empty).flatMap(_._1)
    }

    def flatMap[B](f: A => Reval[F, B]): Reval[F, B] = Reval.FlatMap(this, f)

    def map[B](f: A => B): Reval[F, B] = flatMap(a => Reval.pure(f(a)))

    def combine[B >: A: Semigroup](that: Reval[F, B]): Reval[F, B] = for {
        a <- this
        b <- that
    } yield Semigroup[B].combine(a, b)

    def handleErrorWith[B >: A, E](f: E => Reval[F, B])(implicit
        E: ApplicativeError[F, E]
    ): Reval[F, B] = attempt.flatMap {
        case Right(a) => Reval.pure(a)
        case Left(e) => f(e)
    }

    def attempt[E](implicit F: ApplicativeError[F, E]): Reval[F, Either[E, A]] = this match {
        case self: Reval.Eval[F, A] => Reval.Eval(self.r.attempt)
        case self: Reval.Memoize[F, A] => Reval.Memoize(self.s.attempt, self.key)
        case self: Reval.FlatMap[F, Any, A] =>
            self.s.attempt.flatMap {
                case Left(error) => Reval.pure(Either.left[E, A](error))
                case Right(a) => self.fs(a).attempt
            }
    }

    /**
     * Evaluates `f` before the object is allocated.
     */
    def preAllocate(f: F[Unit]): Reval[F, A] = this match {
        case self: Reval.Memoize[F, A] => Reval.Memoize(self.s.preAllocate(f), self.key)
        case _ => Reval.evalAlways(f).flatMap(_ => this)
    }

    def thunkPreAllocate(f: => Unit)(implicit F: Sync[F]): Reval[F, A] =
        preAllocate(F.delay(f))

    /**
     * Evaluates `f` after the object finalizer is called.
     */
    def postFinalize(f: F[Unit])(implicit F: Applicative[F]): Reval[F, A] = this match {
        case self: Reval.Memoize[F, A] => Reval.Memoize(self.s.postFinalize(f), self.key)
        case _ => Reval.always(F.unit.map(_ -> f)).flatMap(_ => this)
    }

    def thunkPostFinalize(f: => Unit)(implicit F: Sync[F]): Reval[F, A] =
        postFinalize(F.delay(f))

    /**
     * Ensures that the object will be allocated once.
     */
    def memoize[B](implicit ev: A <:< B, B: RevalKeyGen[B]): Reval[F, B] =
        Reval.Memoize(this.map(ev), B.key)
}

object Reval extends RevalInstances {

    /**
     * Constructs `Reval` from allocation and release functions
     * that will be executed once when it is needed.
     */
    def later[F[_]: Functor, A: RevalKeyGen](reval: F[(A, F[Unit])]): Reval[F, A] =
        resourceLater(Resource.apply(reval))

    def makeLater[F[_]: Functor, A: RevalKeyGen](allocate: F[A])(release: A => F[Unit]): Reval[F, A] =
        later(allocate.map(a => a -> release(a)))

    def makeThunkLater[F[_]: Sync, A: RevalKeyGen](allocate: => A)(release: A => Unit): Reval[F, A] =
        later(Sync[F].delay(allocate).map(a => a -> Sync[F].delay(release(a))))

    def resourceLater[F[_], A: RevalKeyGen](r: Resource[F, A]): Reval[F, A] =
        resourceAlways(r).memoize

    /**
     * Constructs `Reval` from an allocation function with no-op release
     * that will be executed once when it is needed.
     */
    def evalLater[F[_], A: RevalKeyGen](f: F[A]): Reval[F, A] =
        resourceLater(Resource.eval(f))

    def thunkLater[F[_]: Sync, A: RevalKeyGen](a: => A): Reval[F, A] =
        evalLater(Sync[F].delay(a))

    /**
     * Constructs `Reval` from allocation and release functions
     * that will be executed always when it is needed.
     */
    def always[F[_]: Functor, A](reval: F[(A, F[Unit])]): Reval[F, A] =
        resourceAlways(Resource.apply(reval))

    def makeAlways[F[_]: Functor, A](allocate: F[A])(release: A => F[Unit]): Reval[F, A] =
        always(allocate.map(a => a -> release(a)))

    def makeThunkAlways[F[_]: Sync, A](allocate: => A)(release: A => Unit): Reval[F, A] =
        always(Sync[F].delay(allocate).map(a => a -> Sync[F].delay(release(a))))

    def resourceAlways[F[_], A](r: Resource[F, A]): Reval[F, A] = Eval(r)

    /**
     * Constructs `Reval` from an allocation function with no-op release
     * that will be executed always when it is needed.
     */
    def evalAlways[F[_], A](f: F[A]): Reval[F, A] = resourceAlways(Resource.eval(f))

    def thunkAlways[F[_]: Sync, A](a: => A): Reval[F, A] = evalAlways(Sync[F].delay(a))

    /**
     * Constructs `Reval` from a pure value with no-op release.
     */
    def pure[F[_], A](a: A): Reval[F, A] = Eval(Resource.pure(a))

    def raiseError[F[_], A, E](e: E)(implicit E: ApplicativeError[F, E]): Reval[F, A] =
        Reval.evalAlways(E.raiseError[A](e))

    final case class FlatMap[F[_], A, +B](s: Reval[F, A], fs: A => Reval[F, B]) extends Reval[F, B]

    final case class Eval[F[_], +A](r: Resource[F, A]) extends Reval[F, A]

    final case class Memoize[F[_], +A](s: Reval[F, A], key: Object) extends Reval[F, A]
}
