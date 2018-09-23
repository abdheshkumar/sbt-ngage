package ngage

import sbt.Keys._
import sbt._

trait CompilerSettings {

  lazy val kindProjectorSettings = Seq(
    libraryDependencies ++= Seq(
      compilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7"),
      compilerPlugin(
        ("org.scalamacros" %% "paradise" % "2.1.0").cross(CrossVersion.patch)
      )
    )
  )

  lazy val scalaMetaSettings = Seq(
    libraryDependencies ++= Seq(
      compilerPlugin(
        ("org.scalameta" % "paradise" % "3.0.0-M11").cross(CrossVersion.full)
      ),
      "org.scalameta" %% "scalameta" % "4.0.0"
    ),
    scalacOptions += "-Xplugin-require:macroparadise",
    scalacOptions in (Compile, console) ~= (_.filterNot(_ contains "paradise")) // macroparadise plugin doesn't work in repl yet.
  )

}
