package jam

import org.scalatest.freespec.AnyFreeSpec

class ReadmeSpec extends AnyFreeSpec {
    "Readme file should be compiled" in {
        class DatabaseAccess()
        class SecurityFilter(val databaseAccess: DatabaseAccess)
        class UserFinder(val databaseAccess: DatabaseAccess, val securityFilter: SecurityFilter)
        class UserStatusReader(val userFinder: UserFinder)
        class PasswordValidator(val databaseAccess: DatabaseAccess, salt: String)
        object PasswordValidator {
            def create(databaseAccess: DatabaseAccess): PasswordValidator =
                new PasswordValidator(databaseAccess, "salt")
        }
        class QuotaChecker(val databaseAccess: DatabaseAccess)

        trait UserModule {
            val singletonDatabaseAccess = jam.brewRec[DatabaseAccess]
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