import mill._
import mill.scalalib._
import mill.scalajslib._
import mill.scalanativelib._
import mill.scalalib.publish._

object core extends Module {
    val versions = new {
        val scala212 = "2.12.13"
        val scala213 = "2.13.4"
        val scalaJs = "1.4.0"
        val scalaNative = "0.4.0"
        val scalatest = "3.2.4-M1"

        val cross = Seq(scala212, scala213)
    }
    trait CommonCoreModule extends PublishModule with CrossScalaModule {
        override def artifactName = "jam-core"
        override def publishVersion = "0.0.1"
        override def pomSettings = PomSettings(
            description = artifactName(),
            organization = "com.github.yakivy",
            url = "https://github.com/yakivy/jam",
            licenses = Seq(License.MIT),
            versionControl = VersionControl.github("yakivy", "jam"),
            developers = Seq(Developer("yakivy", "Yakiv Yereskovskyi", "https://github.com/yakivy"))
        )
        override def compileIvyDeps = Agg(ivy"org.scala-lang:scala-reflect:${scalaVersion()}")
        override def sources = T.sources(millSourcePath / "src")
        override def millSourcePath = super.millSourcePath / os.up
    }
    trait CommonTestModule extends ScalaModule with TestModule {
        override def ivyDeps = super.ivyDeps() ++ Agg(ivy"org.scalatest::scalatest:${versions.scalatest}")
        def testFrameworks = Seq("org.scalatest.tools.Framework")
    }
    object jvm extends Cross[JvmModule](versions.cross: _*)
    class JvmModule(val crossScalaVersion: String) extends CommonCoreModule {
        object test extends CommonTestModule with Tests
    }

    object js extends Cross[JsModule](versions.cross: _*)
    class JsModule(val crossScalaVersion: String) extends CommonCoreModule with ScalaJSModule {
        def scalaJSVersion = versions.scalaJs
        //object test extends CommonTestModule with Tests
    }

    object native extends Cross[NativeModule](versions.cross: _*)
    class NativeModule(val crossScalaVersion: String) extends CommonCoreModule with ScalaNativeModule {
        def scalaNativeVersion = versions.scalaNative
        //object test extends CommonTestModule with Tests
    }
}