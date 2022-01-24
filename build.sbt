name := """play-java-forms-example"""

version := "1.0-SNAPSHOT"

//lazy val root = (project in file(".")).enablePlugins(PlayJava)

//scalaVersion := "2.13.6"

//testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

//libraryDependencies += guice

// disabled until https://github.com/playframework/playframework/issues/9845 is solved
//scalacOptions ++= List("-encoding", "utf8", "-Xfatal-warnings", "-deprecation")
javacOptions ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  filters,
  "mysql" % "mysql-connecter-java" % "5.1.25"
)

play.Project.playJavaSettings
