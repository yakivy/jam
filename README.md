# Jam <img src="https://www.svgrepo.com/show/128194/jam.svg" height="32px" alt="Jam" />
[![Maven Central](https://img.shields.io/maven-central/v/com.github.yakivy/jam-core_2.13.svg)](https://search.maven.org/search?q=g:com.github.yakivy%20jam)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/com.github.yakivy/jam-core_2.13.svg)](https://oss.sonatype.org/content/repositories/snapshots/com/github/yakivy/jam-core_2.13/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
<a href="https://typelevel.org/cats/"><img src="https://typelevel.org/cats/img/cats-badge.svg" height="40px" align="right" alt="Cats friendly" /></a>

Jam is an incredibly simple DI Scala library.

Essential differences from [macwire](https://github.com/softwaremill/macwire):
- is simpler and faster
- searches candidates only in `this`
- supports Scala 3, Scala JS, Scala Native
- supports macro configuration
- provides tools for object lifecycle control

### Table of contents
1. [Quick start](#quick-start)
2. [Brew types](#brew-types)
3. [Implementation details](#implementation-details)
4. [Cats integration](#cats-integration)
5. [Reval monad](#reval-monad)
6. [Macro configuration](#macro-configuration)
7. [Roadmap](#roadmap)
8. [Changelog](#changelog)

### Quick start
Latest stable jam dependency:
```scala
libraryDependencies += Seq(
    "com.github.yakivy" %%% "jam-core" % "0.4.1",
)
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
            new SecurityFilter(singletonDatabaseAccess),
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
- constructor function is being searched in following order:
  - companion apply method that returns a subtype of brewed type in `F[_]` context (with `jam-cats` module)
  - companion apply method that returns a subtype of brewed type
  - class constructor with `@Inject` annotation
  - class constructor
- `val` member works like a singleton provider (instance will be reused for all injections in `this` score), `def` member works like a prototype provider (one method call per each injection)
- library injects only non-implicit constructor arguments; implicits will be resolved by the compiler

### Cats integration
`jam-cats` module provides `brewF` analogies for all `brew` methods using `cats.Monad` typeclass, that allow to brew objects in `F[_]` context, for example: 
```scala
trait UserModule {
    val databaseAccess = jam.brew[DatabaseAccess]
    val maybeSecurityFilter = Option(jam.brew[SecurityFilter])
    val maybeUserStatusReader = jam.cats.brewRecF[Option][UserStatusReader]
}
```
translates to something similar to:
```scala
trait UserModule {
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
```

### Reval monad

`jam-monad` module provides `Reval` monad that encodes the idea of allocating an object which has an associated finalizer. Can be thought of as a mix of `cats.effect.Resource` and `cats.Eval`. It can be useful in cases when you need to control an object lifecycle: how many times the object should be allocated, when it should be allocated and how it should be closed. In the combination with `jam-cats` it should cover most DI cases. For example:
```scala
class DatabaseAccess private ()
object DatabaseAccess {
    def apply: Reval[IO, DatabaseAccess] =
        //to allocate instance once on first request (singleton-like)
        Reval.makeThunkLater {
            println("Creating database access")
            new DatabaseAccess()
        }(_ => println("Closing database access"))
}

class SecurityFilter private (val databaseAccess: DatabaseAccess)
object SecurityFilter {
    def apply(databaseAccess: DatabaseAccess): Reval[IO, SecurityFilter] =
        //to allocate instance on every request (prototype-like)
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

finderModule.finders.usePure.unsafeRunSync()
```
Will produce the following output:
```
Creating database access
Creating security filter
Creating security filter
Closing security filter
Closing security filter
Closing database access
```

### Macro configuration
It's also possible to configure brewing behaviour with an implicit macro JamConfig instance, so here is an example if you for example want to limit recursive brewing only to classes that have "brewable" in the name:
```scala
object myjam extends jam.core.JamCoreDsl with jam.cats.core.JamCatsDsl {
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
        new JamConfig(brewRecRegex = "(?i).*brewable.*")
    }
}
```
then `myjam.brewRec[WithSingleArg]` will throw `Recursive brewing for instance (WithSingleArg).a(WithEmptyArgs) is prohibited from config. WithEmptyArgs doesn't match (?i).*brewable.* regex.` compilation error.

`JamConfig` is a dependent type, so any brew methods that is called from `myjam` object should automatically resolve implicit config without additional imports.

### Roadmap
- fix error message on vacancy for `brewF`
- extract annotation pattern (instead of hardcoded `javax.inject.Inject`) for constructor selection to macro config
- extract method pattern (instead of hardcoded `apply`) for companion constructor selection to macro config
- resolve generic apply method if generics are the same to class constructor

### Changelog

#### 0.4.1
- fix implicit args resolution for jam-cats

#### 0.4.0
- add `jam.monad.Reval` monad
- resolve constructor from companion object

#### 0.3.0
- add `jam.cats` module
- rename `brewWithFrom` to `brewFromWith` and swap arguments
- a couple of minor fixes

#### 0.2.1:
- add member names for ambiguous candidates compilation error
- optimize compilation time for Scala 2.x
- throw compilation error if member type cannot be resolved

#### 0.2.0: :christmas_tree:
- add brewing configuration: `JamConfig`

#### 0.1.0:
- fix import ambiguity: `jam.tree.brew` was renamed to `jam.brewRec`
- fix method overload ambiguity: `jam.brew(f)` was renamed to `jam.brewWith(f)`
- allow passing an instance to brew from (instead of `this`): `jam.brewFrom`
- various refactorings and cleanups
