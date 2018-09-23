lazy val `sbt-ngage` = (project in file("."))
  .settings(moduleName := "sbt-ngage", version in ThisBuild := "0.1-SNAPSHOT")
  .settings(pluginSettings: _*)
