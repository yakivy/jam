package jam

import org.scalatest.freespec.AnyFreeSpec

class ReadmeSpec extends AnyFreeSpec {
    "Readme file should be compiled" in {
        class DatabaseAccess()
        class SecurityFilter(databaseAccess: DatabaseAccess)
        class UserFinder(databaseAccess: DatabaseAccess, securityFilter: SecurityFilter)
        class UserStatusReader(userFinder: UserFinder)

        trait UserModule {
            val singletonDatabaseAccess = jam.tree.brew[DatabaseAccess]
            val userStatusReader = jam.tree.brew[UserStatusReader]
        }
    }
}