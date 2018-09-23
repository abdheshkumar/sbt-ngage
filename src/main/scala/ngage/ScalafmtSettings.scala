package ngage

import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile
import sbt._

trait ScalafmtSettings {

  private val pluginConfigFileName    = "scalafmt.conf"
  private val generatedConfigFileName = ".scalafmt.conf"

  val scalafmtConfigFileCreated =
    taskKey[Boolean]("Check `.scalafmt.conf` file has created or not.")

  val scalafmtGenerateConfigOnLoad =
    settingKey[Unit]("Create `.scalafmt.conf` file in root directory")

  val buildSettingsForScalafmt = Seq(
    scalafmtOnCompile := true,
    scalafmtGenerateConfigOnLoad := FileIOUtil.generateConfigFileFormSource(
      pluginConfigFileName,
      generatedConfigFileName
    ),
    scalafmtConfigFileCreated := FileIOUtil.checkIfFileIsCreated(generatedConfigFileName)
  )

}
