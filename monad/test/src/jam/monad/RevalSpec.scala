package jam.monad

import cats.Semigroupal
import cats.effect.IO
import cats.effect.unsafe.implicits._
import cats.effect.implicits._
import cats.implicits._
import jam.CustomSpec._

class RevalSpec extends CustomMonadSpec {
    "Reval should" - {
        "open resources correct number of times" - {
            "on flatMap" - {
                "for always" in {
                    var ac = 0
                    val b = for {
                        _ <- Reval.thunkAlways[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                        _ <- Reval.thunkAlways[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                        _ <- Reval.thunkAlways[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                    } yield ()
                    val r = Semigroupal[Dep].product(b, b).usePure
                    r.asserting { r =>
                        assert(ac == 6)
                    }
                }
                "for later" in {
                    var ac = 0
                    val b = for {
                        _ <- Reval.thunkLater[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                        _ <- Reval.thunkLater[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                        _ <- Reval.thunkLater[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                    } yield ()
                    val r = Semigroupal[Dep].product(b, b).usePure
                    r.asserting { r =>
                        assert(ac == 3)
                    }
                }
                "for mixed" in {
                    var ac = 0
                    val b = for {
                        _ <- Reval.thunkLater[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                        _ <- Reval.thunkLater[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                        _ <- Reval.thunkAlways[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                    } yield ()
                    val r = Semigroupal[Dep].product(b, b).usePure
                    r.asserting { r =>
                        assert(ac == 4)
                    }
                }
            }
            "in simple module" - {
                "with later" in {
                    object module {
                        var ac = 0
                        val a = Reval.evalLater(IO.delay {
                            ac += 1
                            new WithEmptyArgs()
                        })
                        val b = jam.cats.brewF[Dep][WithSingleArg]
                        val c = jam.cats.brewF[Dep][WithSingleArg]
                        val r = Semigroupal[Dep].product(b, c).usePure
                        val assertion = r.asserting { r =>
                            assert(ac == 1 && r._1.a == r._2.a)
                        }
                    }
                    module.assertion
                }
                "with local anonymous trait" in {
                    case class A()
                    trait B
                    case class C(a: A, b: B)

                    object module {
                        val b = {
                            val b = new B {}
                            b
                        }.pure[Option]

                        val c = List(
                            jam.cats.brewRecF[Option][C],
                        ).sequence.map(_.head)
                        val assertion = assert(c.isDefined)
                    }
                    module.assertion
                }
                "with always" in {
                    object module {
                        var ac = 0
                        val a = Reval.thunkAlways[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                        val b = jam.cats.brewF[Dep][WithSingleArg]
                        val c = jam.cats.brewF[Dep][WithSingleArg]
                        val r = Semigroupal[Dep].product(b, c).usePure
                        val assertion = r.asserting { r =>
                            assert(ac == 2 && r._1.a != r._2.a)
                        }
                    }
                    module.assertion
                }
                "with error" in {
                    object module {
                        val a = Reval.raiseError[IO, WithEmptyArgs, Throwable](new RuntimeException)
                        val b = new WithEmptyArgs()
                        val r = a.handleErrorWith((_: Throwable) => Reval.pure[IO, WithEmptyArgs](b)).usePure
                        val assertion = r.asserting { r =>
                            assert(r == b)
                        }
                    }
                    module.assertion
                }
            }
            "in recursive module" - {
                "for later" in {
                    object module {
                        var ac = 0
                        val a = Reval.thunkLater[IO, WithEmptyArgs] {
                            ac += 1
                            new WithEmptyArgs()
                        }
                        val b = jam.cats.brewRecF[Dep][WithTwoArgLists]
                        val r = b.usePure
                        val assertion = r.asserting { r =>
                            assert(r.b.a == r.c.a && r.b.a == r.c.b.a && ac == 1)
                        }
                    }
                    module.assertion
                }
                "for always" in {
                    object module {
                        var ac = 0
                        val a = Reval.evalAlways(IO.delay {
                            ac += 1
                            new WithEmptyArgs()
                        })
                        val b = jam.cats.brewRecF[Dep][WithTwoArgLists]
                        val r = b.usePure
                        val assertion = r.asserting { r =>
                            assert(r.b.a != r.c.a && r.b.a != r.c.b.a && ac == 3)
                        }
                    }
                    module.assertion
                }
            }
        }
        "run finalizer correct number of times" in {
            var c = 0
            val b = for {
                _ <- Reval.makeThunkLater[IO, Unit](())(_ => c += 1)
                _ <- Reval.makeThunkAlways[IO, Unit](())(_ => c += 1)
            } yield ()
            val r = Semigroupal[Dep].product(b, b).usePure
            r.asserting { r =>
                assert(c == 3)
            }
        }
        "apply pre/post functions in correct order" in {
            var c: List[String] = List.empty
            val a = for {
                _ <- Reval.thunkLater[IO, Unit]((c = "eval" :: c))
                    .thunkPreAllocate((c = "preAllocate" :: c))
                    .thunkPostFinalize((c = "postFinalize" :: c))
                _ <- Reval.thunkAlways[IO, Unit](())
            } yield ()
            val r = Semigroupal[Dep].product(a, a).usePure
            r.asserting { r =>
                assert(c.reverse == List("preAllocate", "eval", "postFinalize"))
            }
        }
        "memoize" in {
            var c = 0
            val a = Reval.thunkAlways[IO, Unit] { c += 1 }.memoize
            val r = Semigroupal[Dep].product(a, a).usePure
            r.asserting { r =>
                assert(c == 1)
            }
        }
        "not compile if later is used in generic method without RavalKeyGen" in {
            assertCompilationErrorMessage(
                assertCompiles("""def a[A] = Reval.thunkLater[IO, A](???)"""),
                "Reval.later cannot resolve concrete result type to delay initialization. " +
                    "Please add implicit RevalKeyGen argument if your type is generic or use Reval.always",
            )
        }
    }
}
