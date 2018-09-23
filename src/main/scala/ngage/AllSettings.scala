package ngage
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt._
import sbt.{ Compile, CrossVersion, Project, Resolver, Setting, State, Test }
import sbtunidoc.BaseUnidocPlugin.autoImport._
import sbtunidoc.ScalaUnidocPlugin.autoImport._
import scoverage.ScoverageKeys
import scalafix.sbt.ScalafixPlugin.autoImport.scalafixSemanticdb

trait AllSettings
    extends CompilerSettings
    with PublishSettings
    with ScalaFixSettings
    with ScalafmtSettings
    with CommonDependencies {

  val scoverageMinimum = 80

  /**
   * Settings common to all projects.
   *
   * Adds Sonatype release repository and "withCachedResolution" to the update options
   */
  lazy val sharedCommonSettings = Seq(
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      Resolver.typesafeIvyRepo("releases"),
      Resolver.bintrayRepo("beyondthelines", "maven")
    ),
    updateOptions := updateOptions.value.withCachedResolution(true)
  )

  /** Settings to make the module not published*/
  lazy val noPublishSettings = Seq(
    publish := ((): Unit),
    publishLocal := ((): Unit),
    publishArtifact := false
  )

  /**
   * Scala JS settings shared by many projects.
   *
   * Forces the use of node.js in tests and batchmode under travis
   */
  lazy val sharedJsSettings = Seq(
    scalaJSStage in Global := FastOptStage,
    parallelExecution := false,
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv()
  )

  /**
   * Build settings common to all projects.
   *
   * scala version and cross versions
   */
  val sharedBuildSettings = Seq(
    organization := "uk.callhandling",
    organizationName := "Call Handling",
    startYear := Some(2017),
    scalaOrganization := "org.scala-lang",
    scalaVersion := "2.12.6",
    crossScalaVersions := Seq("2.12.6", "2.13.0-M4"),
    scalacOptions := crossScalacOptions(scalaVersion.value),
    scalacOptions in (Compile, console) --= Seq(
      "-Ywarn-unused:imports",
      "-Xfatal-warnings"
    ),
    incOptions := incOptions.value.withLogRecompileOnMacro(false)
  )

  def crossScalacOptions(version: String): Seq[String] =
    CrossVersion.partialVersion(version) match {
      case Some((2L, 12L)) =>
        Seq(
          "-Ypartial-unification",
          "-Yno-adapted-args",
          "-opt-warnings",
          "-opt:l:inline",
          "-opt-inline-from:<source>"
        )
      case Some((2L, 11L)) =>
        Seq("-Ypartial-unification", "-Xexperimental", "-Yno-adapted-args")
      case _ => Seq()

    }

  /** Common coverage settings, with minimum coverage defaulting to 80.*/
  def sharedScoverageSettings(min: Double = scoverageMinimum) = Seq(
    ScoverageKeys.coverageMinimum := min,
    ScoverageKeys.coverageFailOnMinimum := true,
    ScoverageKeys.coverageExcludedFiles in Global := ".*<macro>",
    ScoverageKeys.coverageHighlighting := scalaBinaryVersion.value != "2.10"
  )

  lazy val scalafmtSettings: Seq[Setting[_]] = List()

  lazy val scalafixSettings: Seq[Setting[_]] =
    Seq(
      addCompilerPlugin(scalafixSemanticdb),
      scalacOptions ++= Seq("-Yrangepos", "-Ywarn-unused-import")
    )

  /** Common unidoc settings, adding the "-Ymacro-no-expand" scalac option.*/
  lazy val unidocCommonSettings = Seq(
    scalacOptions in (ScalaUnidoc, unidoc) += "-Ymacro-no-expand"
  )

  /**
   * Add a "pretty shell prompt".
   */
  lazy val shellPromptSettings = Seq(shellPrompt := { s: State =>
    val c     = scala.Console
    val blue  = c.RESET + c.BLUE + c.BOLD
    val white = c.RESET + c.BOLD

    val projectName = Project.extract(s).currentProject.id

    s"$blue$projectName$white>${c.RESET}"
  })

}
