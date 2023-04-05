package jam.cats

import cats.Monad
import cats.implicits._
import jam.CustomReadmeSpec
import jam.CustomReadmeSpec._

class ReadmeCoreSpec extends CustomReadmeSpec {
    "Readme examples for cats functionality should be valid" in {
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
}
