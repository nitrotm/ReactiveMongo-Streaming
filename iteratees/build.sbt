import com.typesafe.tools.mima.core._, ProblemFilters._
import com.typesafe.tools.mima.plugin.MimaPlugin.mimaDefaultSettings
import com.typesafe.tools.mima.plugin.MimaKeys.{
  mimaBinaryIssueFilters,
  mimaPreviousArtifacts
}

name := "reactivemongo-iteratees"

lazy val playVer = Def.setting[String] {
  sys.env.get("ITERATEES_VERSION").getOrElse {
    if (scalaVersion.value startsWith "2.11.") "2.3.10"
    else "2.6.1"
  }
}

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-iteratees" % playVer.value % Provided,
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.13" % Test
)

// MiMa
mimaBinaryIssueFilters ++= {
  val dmm = ProblemFilters.exclude[DirectMissingMethodProblem](_)
  val imt = ProblemFilters.exclude[IncompatibleMethTypeProblem](_)
  val pkg = "reactivemongo.play.iteratees"

  Seq(
    dmm(s"${pkg}.PlayIterateesCursorImpl.enumerateResponses"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.enumerateResponses$$default$$1"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.enumerateResponses$$default$$2"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.rawEnumerateResponses"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.rawEnumerateResponses$$default$$1"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.enumerateBulks"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.enumerateBulks$$default$$1"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.enumerateBulks$$default$$2"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.toList"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.toList$$default$$1"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.toList$$default$$2"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.enumerate"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.enumerate$$default$$2"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.enumerate$$default$$1"),
    imt(s"${pkg}.PlayIterateesCursorImpl.collect"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.collect$$default$$1"),
    dmm(s"${pkg}.PlayIterateesCursorImpl.collect$$default$$2"))
}

// Publish
apiURL := Some(url(s"https://reactivemongo.github.io/ReactiveMongo-Streaming/${Publish.majorVersion}/iteratees/api/"))

// Tests
fork in Test := true
