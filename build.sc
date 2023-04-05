import mill._
import mill.scalalib._
import mill.scalajslib._
import mill.scalanativelib._
import mill.scalalib.publish._

object versions {
    val publish = "0.3.0"

    val scala212 = "2.12.17"
    val scala213 = "2.13.10"
    val scala3 = "3.1.3"
    val scalaJs = "1.12.0"
    val scalaNative = "0.4.9"

    val scalatest = "3.2.15"
    val cats = "2.8.0"

    val cross2 = Seq(scala212, scala213)
    val cross = cross2 :+ scala3
}

trait CommonModule extends PublishModule with CrossScalaModule {
    override def publishVersion = versions.publish
    override def pomSettings = PomSettings(
        description = artifactName(),
        organization = "com.github.yakivy",
        url = "https://github.com/yakivy/jam",
        licenses = Seq(License.MIT),
        versionControl = VersionControl.github("yakivy", "jam"),
        developers = Seq(Developer("yakivy", "Yakiv Yereskovskyi", "https://github.com/yakivy"))
    )
    override def compileIvyDeps = T {
        super.compileIvyDeps() ++ {
            if (crossScalaVersion == versions.scala3) Agg.empty[Dep]
            else Agg(ivy"org.scala-lang:scala-reflect:${scalaVersion()}")
        }
    }
    override def scalacOptions = T {
        super.scalacOptions() ++ Seq("-deprecation", "-unchecked") ++ {
            if (scalaVersion() == versions.scala3) Seq("-Xcheck-macros", "-explain")
            else Seq.empty[String]
        }
    }
    override def millSourcePath = super.millSourcePath / os.up
}

trait CommonTestModule extends ScalaModule with TestModule {
    override def ivyDeps = super.ivyDeps() ++ Agg(
        ivy"org.scalatest::scalatest::${versions.scalatest}",
        ivy"javax.inject:javax.inject:1",
    )
    override def compileIvyDeps = T {
        super.ivyDeps() ++ {
            if (scalaVersion() == versions.scala3) Agg.empty[Dep]
            else Agg(ivy"org.scala-lang:scala-reflect:${scalaVersion()}")
        }
    }
    override def testFramework = "org.scalatest.tools.Framework"
}

object core extends Module {
    trait CommonCoreModule extends CommonModule {
        override def artifactName = "jam-core"
    }
    object jvm extends Cross[JvmModule](versions.cross: _*)
    class JvmModule(val crossScalaVersion: String) extends CommonCoreModule {
        object test extends CommonTestModule with Tests
    }

    object js extends Cross[JsModule](versions.cross: _*)
    class JsModule(val crossScalaVersion: String) extends CommonCoreModule with ScalaJSModule {
        def scalaJSVersion = versions.scalaJs
        object test extends CommonTestModule with CrossScalaModuleTests with Tests
    }

    object native extends Cross[NativeModule](versions.cross: _*)
    class NativeModule(val crossScalaVersion: String) extends CommonCoreModule with ScalaNativeModule {
        def scalaNativeVersion = versions.scalaNative
        object test extends CommonTestModule with CrossScalaModuleTests with Tests
    }
}

object cats extends Module {
    trait CommonCatsModule extends CommonModule {
        override def artifactName = "jam-cats"
        override def compileIvyDeps = T {
            super.compileIvyDeps() ++ Agg(
                ivy"org.typelevel::cats-core::${versions.cats}",
            )
        }
    }
    trait CommonCatsTestModule extends CommonTestModule {
        override def ivyDeps = super.ivyDeps() ++ Agg(
            ivy"org.typelevel::cats-core::${versions.cats}",
        )
    }

    object jvm extends Cross[JvmModule](versions.cross: _*)
    class JvmModule(val crossScalaVersion: String) extends CommonCatsModule {
        override def moduleDeps = super.moduleDeps ++ Seq(core.jvm())
        object test extends CommonCatsTestModule with Tests {
            override def moduleDeps = super.moduleDeps ++ Seq(core.jvm().test)
        }
    }

    object js extends Cross[JsModule](versions.cross: _*)
    class JsModule(val crossScalaVersion: String) extends CommonCatsModule with ScalaJSModule {
        def scalaJSVersion = versions.scalaJs
        override def moduleDeps = super.moduleDeps ++ Seq(core.js())
        object test extends CommonCatsTestModule with CrossScalaModuleTests with Tests {
            override def moduleDeps = super.moduleDeps ++ Seq(core.js().test)
        }
    }

    object native extends Cross[NativeModule](versions.cross: _*)
    class NativeModule(val crossScalaVersion: String) extends CommonCatsModule with ScalaNativeModule {
        def scalaNativeVersion = versions.scalaNative
        override def moduleDeps = super.moduleDeps ++ Seq(core.native())
        object test extends CommonCatsTestModule with CrossScalaModuleTests with Tests {
            override def moduleDeps = super.moduleDeps ++ Seq(core.native().test)
        }
    }
}

object `test-helper` extends Module {
    trait CommonTestHelperModule extends CrossScalaModule {
        override def compileIvyDeps = T {
            super.compileIvyDeps() ++ {
                if (crossScalaVersion == versions.scala3) Agg.empty[Dep]
                else Agg(ivy"org.scala-lang:scala-reflect:${scalaVersion()}")
            }
        }
        override def ivyDeps = super.ivyDeps() ++ Agg(
            ivy"org.typelevel::cats-core::${versions.cats}",
        )
        override def millSourcePath = super.millSourcePath / os.up
    }

    object jvm extends Cross[JvmModule](versions.cross: _*)
    class JvmModule(val crossScalaVersion: String) extends CommonTestHelperModule {
        override def moduleDeps = super.moduleDeps ++ Seq(core.jvm(), cats.jvm())
        object test extends CommonTestModule with Tests {
            override def moduleDeps = super.moduleDeps ++ Seq(core.jvm().test)
        }
    }

    object js extends Cross[JsModule](versions.cross: _*)
    class JsModule(val crossScalaVersion: String) extends CommonTestHelperModule with ScalaJSModule {
        def scalaJSVersion = versions.scalaJs
        override def moduleDeps = super.moduleDeps ++ Seq(core.js(), cats.js())
        object test extends CommonTestModule with CrossScalaModuleTests with Tests {
            override def moduleDeps = super.moduleDeps ++ Seq(core.js().test)
        }
    }

    object native extends Cross[NativeModule](versions.cross: _*)
    class NativeModule(val crossScalaVersion: String) extends CommonTestHelperModule with ScalaNativeModule {
        def scalaNativeVersion = versions.scalaNative
        override def moduleDeps = super.moduleDeps ++ Seq(core.native(), cats.native())
        object test extends CommonTestModule with CrossScalaModuleTests with Tests {
            override def moduleDeps = super.moduleDeps ++ Seq(core.native().test)
        }
    }
}
