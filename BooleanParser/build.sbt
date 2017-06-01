
scalaVersion := "0.1.1-20170412-3a9deec-NIGHTLY"

scalaOrganization := "ch.epfl.lamp"

scalaBinaryVersion := "2.11"

ivyScala ~= (_ map (_ copy (overrideScalaVersion = false)))

libraryDependencies += "ch.epfl.lamp" % "dotty_2.11" % scalaVersion.value % "scala-tool"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

scalaCompilerBridgeSource := ("ch.epfl.lamp" % "dotty-sbt-bridge" % scalaVersion.value % "component").sources()