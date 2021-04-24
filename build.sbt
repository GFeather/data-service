import sbt.util

name := "data-service"

Global / lintUnusedKeysOnLoad := false

logLevel := util.Level.Debug

Compile / scalacOptions ++= Seq(
  "-Ylog-classpath",
  "-Xlint"
)

val AkkaVersion = "2.6.13"
val AkkaHttpVersion = "10.2.3"

enablePlugins(JavaAppPackaging)

lazy val commonSettings = Seq(
    organization := "org.github.feather",
    version := "0.1.0",
    scalaVersion := "2.13.4",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.1.0" % Test,
//      "ch.qos.logback" % "logback-classic" % "1.2.3",
//      "org.slf4j" % "slf4j-api" % "1.7.25",
      // log
//      "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion,
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
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,

    )

  )

lazy val query_server = (project in file("query-server"))
  .dependsOn(common)
  .enablePlugins(PlayScala, AkkaGrpcPlugin, JavaAppPackaging)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    commonSettings,
    name := "query-server",
    libraryDependencies ++= Seq(
      "com.zaxxer" % "HikariCP" % "3.4.5",
      "mysql" % "mysql-connector-java" % "8.0.23",
      "io.circe" %% "circe-core" % "0.12.3",
      "io.circe" %% "circe-generic" % "0.12.3",
      "io.circe" %% "circe-parser" % "0.12.3",
      // 1. Basic dependencies for a clustered application
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-cluster-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-cluster-sharding-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,

      // Akka Http
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http2-support" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-discovery" % AkkaVersion,
      // persistence
      "com.typesafe.akka" %% "akka-serialization-jackson" % AkkaVersion,



    )
  )

lazy val common = project
  .settings(
    commonSettings,
    name := "common",
  )