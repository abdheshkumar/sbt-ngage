import org.scalafmt.sbt.ScalafmtPlugin.autoImport.{
  scalafmtConfig,
  scalafmtOnCompile
}
import sbt.Keys._
import sbt.Resolver.sonatypeRepo
import sbt.{AutoPlugin, Def, PluginTrigger, addSbtPlugin, _}
import scalafix.sbt.ScalafixPlugin.autoImport.scalafixConfig
object ProjectPlugin extends AutoPlugin {

  //override def requires: Plugins = NgagePlugin

  override def trigger: PluginTrigger = allRequirements

  object autoImport {

    lazy val commonSettings: Seq[Def.Setting[_]] = Seq(
      name in ThisBuild := "sbt-ngage",
      organization in ThisBuild := "uk.callhandling",
      organizationName in ThisBuild := "Call Handling",
      crossScalaVersions in ThisBuild := Seq("2.12.6", "2.13.0-M4"),
      scalaVersion in ThisBuild := "2.12.6",
      scalafmtOnCompile := true,
      scalafmtConfig := Some(
        baseDirectory.value / "src" / "main" / "resources" / "scalafmt.conf"
      ),
      scalafixConfig := Some(
        baseDirectory.value / "src" / "main" / "resources" / "scalafix.conf"
      )
    )

    lazy val pluginSettings: Seq[Def.Setting[_]] = commonSettings ++ Seq(
      sbtPlugin := true,
      resolvers ++= Seq(sonatypeRepo("snapshots"), sonatypeRepo("releases")),
      addSbtPlugin("com.eed3si9n" % "sbt-unidoc" % "0.4.2"),
      addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.9"),
      addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.1"),
      addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0"),
      addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.0-M3"),
      addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.24"),
      addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.0-RC1"),
      addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.6.0-RC4")
    )

  }

  override def projectSettings: Seq[Def.Setting[_]] = artifactSettings

  private[this] val artifactSettings = Seq(
    scalaVersion := "2.12.6",
    scalaOrganization := "org.scala-lang",
    startYear := Some(2017)
  )
}
