package jam

import jam.CustomReadmeSpec._
import org.scalatest.freespec.AnyFreeSpec

class ReadmeCoreSpec extends AnyFreeSpec {
    "Readme examples for core functionality should be valid" in {
        trait UserModule {
            val singletonDatabaseAccess = jam.brew[DatabaseAccess]
            val userStatusReader = jam.brewRec[UserStatusReader]
        }

        trait PasswordValidatorModule extends UserModule {
            val passwordValidator = jam.brewWith(PasswordValidator.create _)
        }

        trait QuotaCheckerModule {
            object ResolvedUserModule extends UserModule

            val quotaChecker = jam.brewFrom[QuotaChecker](ResolvedUserModule)
        }

        new PasswordValidatorModule with QuotaCheckerModule {
            assert(userStatusReader.userFinder.databaseAccess.eq(singletonDatabaseAccess))
            assert(passwordValidator.databaseAccess.eq(singletonDatabaseAccess))
            assert(quotaChecker.databaseAccess.eq(ResolvedUserModule.singletonDatabaseAccess))
        }
    }
}