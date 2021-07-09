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

        trait UserModule {
            val singletonDatabaseAccess = jam.tree.brew[DatabaseAccess]
            val userStatusReader = jam.tree.brew[UserStatusReader]
        }

        trait PasswordValidatorModule extends UserModule {
            val passwordValidator = jam.tree.brew(PasswordValidator.create _)
        }

        new PasswordValidatorModule {
            assert(userStatusReader.userFinder.databaseAccess.eq(singletonDatabaseAccess))
            assert(passwordValidator.databaseAccess.eq(singletonDatabaseAccess))
        }
    }
}