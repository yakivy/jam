import mill._
import mill.scalalib._
import mill.scalajslib._
import mill.scalanativelib._
import mill.scalalib.publish._

object versions {
    val publish = "0.2.1"

    val scala212 = "2.12.17"
    val scala213 = "2.13.10"
    val scala3 = "3.1.3"
    val scalaJs = "1.12.0"
    val scalaNative = "0.4.8"
    val scalatest = "3.2.14"

    val cross2 = Seq(scala212, scala213)
    val cross = cross2 :+ scala3
}

object core extends Module {
    trait CommonCoreModule extends PublishModule with CrossScalaModule {
        override def artifactName = "jam-core"
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
            super.scalacOptions() ++ List(
                "-deprecation",
                "-unchecked",
            )
        }
        override def millSourcePath = super.millSourcePath / os.up
    }
    trait CommonCoreTestModule extends ScalaModule with TestModule {
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
    object jvm extends Cross[JvmModule](versions.cross: _*)
    class JvmModule(val crossScalaVersion: String) extends CommonCoreModule {
        object test extends CommonCoreTestModule with Tests {
            override def moduleDeps = super.moduleDeps ++ Seq(`test-helper`.jvm())
        }
    }

    object js extends Cross[JsModule](versions.cross: _*)
    class JsModule(val crossScalaVersion: String) extends CommonCoreModule with ScalaJSModule {
        def scalaJSVersion = versions.scalaJs
        object test extends CommonCoreTestModule with CrossScalaModuleTests with Tests {
            override def moduleDeps = super.moduleDeps ++ Seq(`test-helper`.js())
        }
    }

    object native extends Cross[NativeModule](versions.cross: _*)
    class NativeModule(val crossScalaVersion: String) extends CommonCoreModule with ScalaNativeModule {
        def scalaNativeVersion = versions.scalaNative
        object test extends CommonCoreTestModule with CrossScalaModuleTests with Tests {
            override def moduleDeps = super.moduleDeps ++ Seq(`test-helper`.native())
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
        override def millSourcePath = super.millSourcePath / os.up
    }
    object jvm extends Cross[JvmModule](versions.cross: _*)
    class JvmModule(val crossScalaVersion: String) extends CommonTestHelperModule {
        override def moduleDeps = super.moduleDeps ++ Seq(core.jvm())
    }

    object js extends Cross[JsModule](versions.cross: _*)
    class JsModule(val crossScalaVersion: String) extends CommonTestHelperModule with ScalaJSModule {
        def scalaJSVersion = versions.scalaJs
        override def moduleDeps = super.moduleDeps ++ Seq(core.js())
    }

    object native extends Cross[NativeModule](versions.cross: _*)
    class NativeModule(val crossScalaVersion: String) extends CommonTestHelperModule with ScalaNativeModule {
        def scalaNativeVersion = versions.scalaNative
        override def moduleDeps = super.moduleDeps ++ Seq(core.native())
    }
}
