package ngage
import java.nio.file.{Files, Paths}

import sbt.{IO, MessageOnlyException, file}

import scala.io.Source

object FileIOUtil {

  def generateConfigFileFormSource(sourceName: String, fileName: String): Unit =
    IO.write(
      file(fileName),
      Source
        .fromInputStream(getClass.getResourceAsStream(s"/$sourceName"))
        .mkString
    )

  def checkIfFileIsCreated(fileName: String): Boolean =
    if (Files.exists(Paths.get(fileName)))
      true
    else
      throw new MessageOnlyException(s"$fileName file doesn't exist.")
}
