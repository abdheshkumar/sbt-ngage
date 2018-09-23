package ngage
import sbt._

trait ScalaFixSettings {
  private val pluginConfigFileName = "scalafix.conf"
  private val generatedConfigFileName = ".scalafix.conf"

  val scalafixConfigFileCreated = taskKey[Boolean](
    "Check scalafix file `.scalafix.conf` file has created or not."
  )

  val scalafixGenerateConfigOnLoad =
    settingKey[Unit]("Create `.scalafix.conf` file")

  val buildSettingsForScalaFix = Seq(
    scalafixGenerateConfigOnLoad := FileIOUtil.generateConfigFileFormSource(
      pluginConfigFileName,
      generatedConfigFileName
    ),
    scalafixConfigFileCreated := FileIOUtil.checkIfFileIsCreated(
      generatedConfigFileName
    )
  )

}
