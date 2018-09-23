package ngage

import sbt._, Keys._

trait PublishSettings {

  /** Adds the credential settings required for sonatype releases.*/
  lazy val sonataCredentials = for {
    username <- sys.env.get("SONATYPE_USERNAME")
    password <- sys.env.get("SONATYPE_PASSWORD")
  } yield
    Credentials(
      "Sonatype Nexus Repository Manager",
      "oss.sonatype.org",
      username,
      password
    )

  val buildSettingsForPublish = Seq(
    credentials ++= sonataCredentials.toSeq,
    ThisBuild / isSnapshot / publishTo :=
      Some(
        "snapshots"
          .at("https://oss.sonatype.org/content/repositories/snapshots")
      ),
    ThisBuild / publishTo :=
      Some(
        "releases"
          .at("https://oss.sonatype.org/service/local/staging/deploy/maven2")
      )
  )

}
