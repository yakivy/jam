package jam.monad

import cats.effect.IO
import cats.effect.unsafe.implicits._
import cats.implicits._

class RevalExampleSpec extends CustomMonadSpec {
    "Reval examples should be valid" - {
        "in class doc" - {
            "for later" in {
                var c = 0
                val a = Reval.thunkLater[IO, Int] { c += 1; c }
                val b = a.replicateA(3).map(_.sum).usePure

                b.asserting(b => assert(b == 3 && c == 1))
            }
            "for always" in {
                var c = 0
                val a = Reval.thunkAlways[IO, Int] { c += 1; c }
                val b = a.replicateA(3).map(_.sum).usePure

                b.asserting(b => assert(c == 3 && b == 6))
            }
            "common pitfalls" - {
                "for later with multiple uses" in {
                    var c = 0
                    val a = Reval.thunkLater[IO, Int] { c += 1; c }
                    val b = (a.usePure, a.usePure).mapN(_ + _)

                    b.asserting(b => assert(c == 2 && b == 3))
                }
            }
        }
        "in readme" in {
            object module {
                class DatabaseAccess private ()
                object DatabaseAccess {
                    val apply: Reval[IO, DatabaseAccess] =
                        Reval.makeThunkLater {
                            println("Creating database access")
                            new DatabaseAccess()
                        }(_ => println("Closing database access"))
                }

                class SecurityFilter private (val databaseAccess: DatabaseAccess)
                object SecurityFilter {
                    def apply(databaseAccess: DatabaseAccess): Reval[IO, SecurityFilter] =
                        Reval.makeThunkAlways {
                            println("Creating security filter")
                            new SecurityFilter(databaseAccess)
                        }(_ => println("Closing security filter"))
                }

                class UserFinder(val databaseAccess: DatabaseAccess, val securityFilter: SecurityFilter)
                class OrganizationFinder(val databaseAccess: DatabaseAccess, val securityFilter: SecurityFilter)

                trait FinderModule {
                    val finders = (
                        jam.cats.brewRecF[Reval[IO, *]][UserFinder],
                        jam.cats.brewRecF[Reval[IO, *]][OrganizationFinder],
                    ).tupled
                }

                val r = new FinderModule {}.finders.usePure
                val assertion = r.asserting { r =>
                    assert(r._1.securityFilter != r._2.securityFilter)
                    assert(r._1.databaseAccess == r._2.databaseAccess)
                }
            }
            module.assertion
        }
    }
}
