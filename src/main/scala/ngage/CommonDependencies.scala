package ngage
import sbt._, Keys._

trait CommonDependencies {

  private val libs = Seq("org.scalacheck" %% "scalacheck" % "1.14.0" % Test)

  /**
    * Common dependencies to all projects.
    *
    */
  lazy val sharedCommonDependencies = Seq(libraryDependencies ++= libs)
}
