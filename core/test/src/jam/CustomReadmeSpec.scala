package jam

object CustomReadmeSpec {
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
}

trait CustomReadmeSpec extends CustomSpec
