package ngage
import sbt.Setting

trait DefaultSettings extends AllSettings {
  lazy val orgDefaultSettings: Seq[Setting[_]] =
    sharedCommonSettings ++
      sharedCommonDependencies ++
      buildSettingsForScalaFix ++
      buildSettingsForPublish ++
      buildSettingsForScalafmt ++
      sharedBuildSettings ++
      shellPromptSettings ++
      sharedScoverageSettings() ++
      scalafmtSettings ++
      kindProjectorSettings ++
      scalaMetaSettings ++
      scalafixSettings ++
      scalafmtSettings
}
