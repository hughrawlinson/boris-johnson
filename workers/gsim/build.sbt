import scala.util.parsing.json._

val nexus = Map(
  "snapshots" -> ("snapshots" at "https://releases.service.improbable.io/content/repositories/snapshots/"),
  "releases" -> ("releases" at "https://releases.service.improbable.io/content/repositories/releases/"))

resolvers.in(Global) ++= nexus.values.toSeq

credentials.in(Global) ++= (Path.userHome / ".ivy2" / "improbable.credentials").get.map(Credentials(_))

val projectManifestText = IO.read(file("../../spatialos.json")).trim
val projectManifestObj:Option[Any] = JSON.parseFull(projectManifestText)
val projectManifest:Map[String,Any] = projectManifestObj.get.asInstanceOf[Map[String, Any]]
val currentVersion = projectManifest.get("project_version").get.asInstanceOf[String]
val improbableVersion = scala.util.Properties.envOrNone("SPATIALOS_BUILD_NUMBER").getOrElse(projectManifest.get("sdk_version").get.asInstanceOf[String])
val projectName = projectManifest.get("name").get.asInstanceOf[String]

lazy val rootProject = Project(id = projectName, base = file("."))
  .settings(scalaVersion := "2.11.7")
  .settings(version := currentVersion)
  .settings(libraryDependencies += "improbable" %% "deployment" % improbableVersion)
  .settings(libraryDependencies += "improbable" %% "corelibrary" % improbableVersion)
  .settings(unmanagedSourceDirectories.in(Compile) += baseDirectory.value / "generated")
  .settings(libraryDependencies += "improbable" %% "universal-tiny-client" % improbableVersion)
  .settings(compile <<= (compile in Compile) dependsOn (copyResources in Compile))

packAutoSettings

packJarNameConvention := "full"

packExcludeJars := Seq(
  "scala-compiler-.*\\.jar",
  "arch-util_2.11.*\\.jar",
  "fabric-core_2.11.*\\.jar",
  "deployment_2.11.*\\.jar"
)

pack := {
  val packDir = pack.value
  val libDir = packDir / "lib"
  val outputDir = (baseDirectory.value / ".." / ".." / "build" / "assembly" / "gsim").getCanonicalFile
  if (outputDir.exists()) {
    streams.value.log.info(s"Cleaning output directory $outputDir")
    IO.delete(IO.listFiles(outputDir))
  } else {
    IO.createDirectory(outputDir)
  }
  streams.value.log.info(s"Copying $libDir to $outputDir")
  IO.listFiles(libDir).foreach { f =>
    val destFile = new File(outputDir, f.getName().replaceAll("[^a-zA-Z0-9_@.]", "_"))
    IO.copyFile(f, destFile);
  }
  outputDir
}

fork in runMain := false
