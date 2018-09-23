package ngage

import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile
import sbt._

trait ScalafmtSettings {

  private val pluginConfigFileName    = "scalafmt.conf"
  private val generatedConfigFileName = ".scalafmt.conf"

  val scalafmtConfigFileCreated =
    TaskKey[Boolean]("scalafmtConfigFileCreated")

  val scalafmtGenerateConfigOnLoad =
    SettingKey[Unit]("scalafmtGenerateConfigOnLoad")

  val buildSettingsForScalafmt = Seq(
    scalafmtOnCompile := true,
    scalafmtGenerateConfigOnLoad := FileIOUtil.generateConfigFileFormSource(
      pluginConfigFileName,
      generatedConfigFileName
    ),
    scalafmtConfigFileCreated := FileIOUtil.checkIfFileIsCreated(generatedConfigFileName)
  )

}
