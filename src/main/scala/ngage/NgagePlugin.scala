package ngage

import org.scalafmt.sbt.ScalafmtPlugin
import sbt._
import scalafix.sbt.ScalafixPlugin
import scoverage.ScoverageSbtPlugin

object NgagePlugin extends AutoPlugin {

  object autoImport extends DefaultSettings

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins =
    plugins.JvmPlugin &&
      ScalafmtPlugin && ScalafixPlugin && ScoverageSbtPlugin

  override def projectSettings: Seq[Setting[_]] = autoImport.orgDefaultSettings
}
