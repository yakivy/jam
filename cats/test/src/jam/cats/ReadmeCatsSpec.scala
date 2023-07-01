package jam.cats

import cats.Monad
import cats.implicits._
import jam.CustomReadmeSpec
import jam.CustomReadmeSpec._
import org.scalatest.freespec.AnyFreeSpec

class ReadmeCatsSpec extends AnyFreeSpec with CustomReadmeSpec {
    "Readme examples should be valid" - {
        "for core functionality" in {
            trait UserModule {
                val databaseAccess = jam.brew[DatabaseAccess]
                val maybeSecurityFilter = Option(jam.brew[SecurityFilter])
                val maybeUserStatusReader = jam.cats.brewRecF[Option][UserStatusReader]
            }

            trait TranslatedUserModule {
                val databaseAccess = new DatabaseAccess()
                val maybeSecurityFilter = Option(new SecurityFilter(databaseAccess))
                val maybeUserStatusReader = (
                    Monad[Option].pure(databaseAccess),
                    maybeSecurityFilter,
                ).mapN((databaseAccess, securityFilter) => new UserStatusReader(
                    new UserFinder(
                        databaseAccess,
                        securityFilter,
                    )
                ))
            }

            new UserModule {
                assert(maybeUserStatusReader.get.userFinder.securityFilter.eq(maybeSecurityFilter.get))
                assert(maybeUserStatusReader.get.userFinder.databaseAccess.eq(databaseAccess))
            }
        }
        "for troubleshooting" in {
            case class A()
            case class B()
            case class C(a: A, b: B)
            object Module {
                val a = Option(A())
                val b = Option(B())
                /*val c = a.flatMap(a =>
                    jam.cats.brewWithF[Option]((b: B) => C(a /*closure*/ , b))
                )*/
                val c = a.flatMap(a =>
                    jam.cats.brewWithF[Option]((b: B) => C(_, b)).map(_.apply(a) /*closure*/)
                )
                assert(c.get.a.eq(a.get))
                assert(c.get.b.eq(b.get))
            }
        }
    }
}
