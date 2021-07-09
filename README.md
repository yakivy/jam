# Jam <img src="https://www.svgrepo.com/show/128194/jam.svg" height="32px" alt="Jam" />
[![Maven Central](https://img.shields.io/maven-central/v/com.github.yakivy/jam-core_2.13.svg)](https://search.maven.org/search?q=g:com.github.yakivy%20jam)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/com.github.yakivy/jam-core_2.13.svg)](https://oss.sonatype.org/content/repositories/snapshots/com/github/yakivy/jam-core_2.13/)
[![Build Status](https://travis-ci.com/yakivy/jam.svg?branch=master)](https://travis-ci.com/yakivy/jam)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Jam is an incredibly simple DI Scala library.

Essential differences from [macwire](https://github.com/softwaremill/macwire):
- is able to inject a whole object tree, not only constructor arguments
- searches candidates in `this`
- supports Scala 3, Scala JS, Scala Native
- incredibly simple: 2 exposed methods, ~100 lines of code

### Quick start
Latest stable jam dependency:
```scala
"com.github.yakivy" %% "jam-core" % "0.0.10"
```
Usage example:
```scala
class DatabaseAccess()
class SecurityFilter(databaseAccess: DatabaseAccess)
class UserFinder(databaseAccess: DatabaseAccess, securityFilter: SecurityFilter)
class UserStatusReader(userFinder: UserFinder)

trait UserModule {
    val singletonDatabaseAccess = jam.tree.brew[DatabaseAccess]
    val userStatusReader = jam.tree.brew[UserStatusReader]
}
```
Macro output:
```scala
trait UserModule {
    val singletonDatabaseAccess = new DatabaseAccess()
    val userStatusReader = new UserStatusReader(
        new UserFinder(
            singletonDatabaseAccess,
            new SecurityFilter(singletonDatabaseAccess)
        )
    )
}
```
### Brew types
- `jam.brew` - injects constructor arguments if they are provided in `this`, otherwise throws an error
- `jam.tree.brew` - injects constructor arguments if they are provided in `this` or recursively brews them

### Brew function
If the constructor cannot be resolved automatically or you just need to create an object with an arbitrary function, it is possible to use brew with argument:
```scala
class PasswordValidator(databaseAccess: DatabaseAccess, salt: String)
object PasswordValidator {
    def create(databaseAccess: DatabaseAccess): PasswordValidator =
        new PasswordValidator(atabaseAccess, "salt")
}

trait PasswordValidatorModule extends UserModule {
    val passwordValidator = jam.tree.brew(PasswordValidator.create _)
}
```

### Implementation details 
- injection candidates is being searched in `this` instance, so to provide an instance for future injection you need to make it a member of `this`. Examples:
```scala
trait A {
    val a = new A
    ...brewing //val a will be used
}

val container = new {
    val a = new A
    ...brewing //val a will be used
}

trait A {
    def b(): String = {
        val a = new A
        ...brewing //new A instance will be created
    }
}

trait A {
    val a1 = new A
    {
        val a2 = new A
        ...brewing //val a1 will be used
    }
}
```
- library injects only non implicit constructor arguments, implicits will be resolved by compiler
- jam is intended to be minimal, features like scopes or object lifecycles should be implemented manually, for example:
```scala
//to release resources use cats.effect.Resource
dbClientResource.use { dbClient =>
    val container = new {
        //to create a singleton use val member
        val userStorage = new UserStorage(dbClient)

        ...brewing
    }
    ...logic that utilizes container
} // client will be closed
```