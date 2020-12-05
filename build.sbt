ThisBuild / version := "0.0.1"
ThisBuild / organization := "io.github.leoframework"

ThisBuild / homepage := Some(url("https://github.com/leoframework/"))
ThisBuild / licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
ThisBuild / developers := List(
	Developer("sirinath", "Suminda Sirinath Salpitikorala Dharmasena", "me@sirinath.com", url("http://sirinath.com"))
)

ThisBuild / scalaVersion := "3.0.0-M2"

lazy val commonSettings = Seq(
	resolvers ++= Seq(
		Resolver.mavenLocal,
		DefaultMavenRepository,
		Resolver.jcenterRepo,
		Resolver.sonatypeRepo("releases"),
		Resolver.typesafeRepo("releases"),
		Resolver.sbtPluginRepo("releases"),
		JavaNet2Repository
	),
	javacOptions ++= Seq("-source", "14", "-target", "11")
)

lazy val root = project
  .in(file("."))
  .aggregate(engine)
  .dependsOn(engine)
  .aggregate(logging)
  .dependsOn(logging)
  .settings(
	commonSettings,
    name := "Leo Framework",
    description := "Leo Framework: Template based web framework and static site generator"
  )
  
lazy val subprojectSettings = Seq(
)
  
lazy val engine = project
  .in(file("engine"))
  .dependsOn(logging)
  .settings(
	commonSettings,
	subprojectSettings,
    name := "Leo Framework: Engine",
    description := "Leo Framework: Engine - Generator and server engine",
		libraryDependencies ++= Seq(
		)
	)
  
lazy val logging = project
  .in(file("logging"))
  .settings(
	commonSettings,
	subprojectSettings,
    name := "Leo Framework: Logging",
    description := "Leo Framework: Logging"
  )

lazy val utils = project
  .in(file("utils"))
  .settings(
    commonSettings,
    subprojectSettings,
    name := "Leo Framework: Utilities",
    description := "Leo Framework: Utilities",
		libraryDependencies ++= Seq(
      "com.conversantmedia" % "disruptor" % "1.2.19"
		)
  )