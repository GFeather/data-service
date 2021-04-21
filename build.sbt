import sbt.util

name := "data-service"

Global / lintUnusedKeysOnLoad := false

logLevel := util.Level.Debug

lazy val akkaVersion = "2.6.10"

enablePlugins(JavaAppPackaging)

lazy val commonSettings = Seq(
    organization := "org.github.feather",
    version := "0.1.0",
    scalaVersion := "2.13.4",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.9.1",
      "com.chuusai" %% "shapeless" % "2.3.3",
      guice
    ),
)

lazy val management = project
  .dependsOn(common)
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    commonSettings,
    name := "management",
    idePackagePrefix := Some("org.github.feather.management"),
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play" % "2.8.7",
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.slick" %% "slick" % "3.3.3",
      "com.typesafe.slick" %% "slick-codegen" % "3.3.3",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
      "org.postgresql" % "postgresql" % "42.2.18",

    ),

)

lazy val gateway = project
  .dependsOn(common)
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    commonSettings,
    name := "gateway",
    idePackagePrefix := Some("org.github.feather.gateway"),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
      "org.scalatest" %% "scalatest" % "3.1.0" % Test
    )

  )

lazy val query_server = (project in file("query-server"))
  .dependsOn(common)
  .enablePlugins(PlayScala, AkkaGrpcPlugin, JavaAppPackaging)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    commonSettings,
    name := "query-server",
    idePackagePrefix := Some("org.github.feather.queryServer"),
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.zaxxer" % "HikariCP" % "4.0.3",
      "mysql" % "mysql-connector-java" % "8.0.23",
      "io.circe" %% "circe-core" % "0.12.3",
      "io.circe" %% "circe-generic" % "0.12.3",
      "io.circe" %% "circe-parser" % "0.12.3",
      "org.scalatest" %% "scalatest" % "3.1.0" % Test
    )
  )

lazy val common = project
  .settings(
    commonSettings,
    name := "common",
    idePackagePrefix := Some("org.github.feather.common"),
  )