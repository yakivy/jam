import mill._
import mill.scalalib._
import mill.scalajslib._
import mill.scalanativelib._
import mill.scalalib.publish._

object core extends Module {
    val versions = new {
        val scala212 = "2.12.13"
        val scala213 = "2.13.4"
        val scala3 = "3.0.0-RC2"
        val scalaJs = "1.5.0"
        val scalaNative = "0.4.0"
        val scalatest = "3.2.7"

        val cross2 = Seq(scala212, scala213)
        val cross3 = Seq(scala3)
        val cross = cross2 ++ cross3
    }
    trait CommonCoreModule extends PublishModule with CrossScalaModule {
        override def artifactName = "jam-core"
        override def publishVersion = "0.0.3"
        override def pomSettings = PomSettings(
            description = artifactName(),
            organization = "com.github.yakivy",
            url = "https://github.com/yakivy/jam",
            licenses = Seq(License.MIT),
            versionControl = VersionControl.github("yakivy", "jam"),
            developers = Seq(Developer("yakivy", "Yakiv Yereskovskyi", "https://github.com/yakivy"))
        )
        override def compileIvyDeps =
            if (crossScalaVersion == versions.scala3) Agg.empty[Dep]
            else Agg(ivy"org.scala-lang:scala-reflect:${scalaVersion()}")
        override def millSourcePath = super.millSourcePath / os.up
    }
    trait CommonTestModule extends ScalaModule with TestModule {
        override def ivyDeps = super.ivyDeps() ++ Agg(ivy"org.scalatest::scalatest::${versions.scalatest}")
        def testFrameworks = Seq("org.scalatest.tools.Framework")
    }
    object jvm extends Cross[JvmModule](versions.cross: _*)
    class JvmModule(val crossScalaVersion: String) extends CommonCoreModule {
        object test extends CommonTestModule with Tests
    }

    object js extends Cross[JsModule](versions.cross: _*)
    class JsModule(val crossScalaVersion: String) extends CommonCoreModule with ScalaJSModule {
        def scalaJSVersion = versions.scalaJs
        object test extends CommonTestModule with Tests
    }

    object native extends Cross[NativeModule](versions.cross2: _*)
    class NativeModule(val crossScalaVersion: String) extends CommonCoreModule with ScalaNativeModule {
        def scalaNativeVersion = versions.scalaNative
        object test extends CommonTestModule with Tests
    }
}