package ngage
import sbt.{ SettingKey, TaskKey }

trait ScalaFixSettings {
  private val pluginConfigFileName    = "scalafix.conf"
  private val generatedConfigFileName = ".scalafix.conf"
  val scalafixConfigFileCreated       = TaskKey[Boolean]("scalafixConfigFileCreated")

  val scalafixGenerateConfigOnLoad =
    SettingKey[Unit]("scalafixGenerateConfigOnLoad")

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
