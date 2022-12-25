# Jam <img src="https://www.svgrepo.com/show/128194/jam.svg" height="32px" alt="Jam" />
[![Maven Central](https://img.shields.io/maven-central/v/com.github.yakivy/jam-core_2.13.svg)](https://search.maven.org/search?q=g:com.github.yakivy%20jam)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/com.github.yakivy/jam-core_2.13.svg)](https://oss.sonatype.org/content/repositories/snapshots/com/github/yakivy/jam-core_2.13/)
[![Build Status](https://travis-ci.com/yakivy/jam.svg?branch=master)](https://travis-ci.com/yakivy/jam)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Jam is an incredibly simple DI Scala library.

Essential differences from [macwire](https://github.com/softwaremill/macwire):
- is simpler, faster and more transparent
- supports Scala 3, Scala JS, Scala Native
- searches candidates in `this`
- supports macro config

### Quick start
Latest stable jam dependency:
```scala
"com.github.yakivy" %% "jam-core" % "0.2.0"
```
Usage example:
```scala
class DatabaseAccess()
class SecurityFilter(databaseAccess: DatabaseAccess)
class UserFinder(databaseAccess: DatabaseAccess, securityFilter: SecurityFilter)
class UserStatusReader(userFinder: UserFinder)

trait UserModule {
    val singletonDatabaseAccess = jam.brew[DatabaseAccess]
    val userStatusReader = jam.brewRec[UserStatusReader]
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
- `jam.brewRec` - injects constructor arguments if they are provided in `this` or recursively brews them
- `jam.brewWith` - injects lambda arguments if they are provided in `this`, otherwise throws an error, especially useful when the constructor cannot be resolved automatically:
```scala
class PasswordValidator(databaseAccess: DatabaseAccess, salt: String)
object PasswordValidator {
    def create(databaseAccess: DatabaseAccess): PasswordValidator =
        new PasswordValidator(databaseAccess, "salt")
}

trait PasswordValidatorModule extends UserModule {
    val passwordValidator = jam.brewWith(PasswordValidator.create _)
}
```
- `jam.brewFrom` - injects constructor arguments if they are provided in `self` argument, otherwise throws an error:
```scala
class QuotaChecker(databaseAccess: DatabaseAccess)

trait QuotaCheckerModule {
    object ResolvedUserModule extends UserModule

    val quotaChecker = jam.brewFrom[QuotaChecker](ResolvedUserModule)
}
```
### Macro configuration
It's also possible to configure brewing behaviour with an implicit macro JamConfig instance, so here is an example if you for example want to limit recursive brewing only to classes that have "brewable" in the name:
```
object myjam extends jam.JamDsl {
    //for Scala 2.x
    //and don't forget about Scala 2 macro system requirements:
    //- define macro in a separate compilation unit
    //- add `scala.language.experimental.macros` import
    //- add `org.scala-lang:scala-reflect` compile time dependency
    def myJamConfigImpl(c: blackbox.Context): c.Tree = c.universe.reify {
        new JamConfig(brewRecRegex = "(?i).*brewable.*")
    }.tree
    implicit def myJamConfig: JamConfig = macro myJamConfigImpl

    //for Scala 3.x
    implicit inline def myJamConfig: JamConfig = {
        JamConfig(brewRecRegex = "(?i).*brewable.*")
    }
}
```
then `myjam.brewRec[WithSingleArg]` will throw `Recursive brewing for instance (WithSingleArg).a(WithEmptyArgs) is prohibited from config. WithEmptyArgs doesn't match (?i).*brewable.* regex.` compilation error. `JamConfig` is a dependent type, so all brew methods that are called from `myjam` object should automatically resolve implicit config without any imports.

### Implementation details 
- injection candidates are being searched in `this` instance, so to provide an instance for future injection, you need to make it a member of `this`. Examples:
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
        ...brewing //val a will be ignored
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
- `val` member works like a singleton provider (instance will be reused for all injections in `this` score), `def` member works like a prototype provider (one method call per each injection)
- library injects only non-implicit constructor arguments; implicits will be resolved by the compiler
- jam is intended to be minimal; features like scopes or object lifecycles should be implemented manually

### Changelog

#### 0.2.0: :christmas_tree:
- add brewing configuration: `JamConfig`

#### 0.1.0:
- fix import ambiguity: `jam.tree.brew` was renamed to `jam.brewRec`
- fix method overload ambiguity: `jam.brew(f)` was renamed to `jam.brewWith(f)`
- allow passing an instance to brew from (instead of `this`): `jam.brewFrom`
- various refactorings and cleanups
