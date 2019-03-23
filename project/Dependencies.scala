import sbt._


//为了引入子模块growing-micros添加的文件 
object Dependencies {

  object Versions {
    val i18n = "1.0.5"
    val play = "2.6.13"
    val grpc = "1.17.1"
    val redis = "1.8.0"
    val config = "1.3.3"
    val hashids = "1.0.3"
    val log4j2 = "2.11.0"
    val jackson = "2.9.5"
    val scala212 = "2.12.8"
    val scalatest = "3.0.5"
    val scalaI18n = "1.0.6"
    val urlParser = "0.2.0"
    val scala211 = "2.11.11"
    val httpclient = "2.4.5"
    val dryad = "1.0.9-SNAPSHOT"
    val spring = "5.0.2.RELEASE"
    val slick = "3.2.3"
    val playSlick = "3.0.3"
    val slickPg = "0.17.0"
  }

  import Compiles._

  object Compiles {
    val i18n = "org.jmotor" %% "scala-i18n" % Versions.i18n

    val hashids = "org.hashids" % "hashids" % Versions.hashids

    val config = "com.typesafe" % "config" % Versions.config % Provided

    val dryad = "io.growing" %% "dryad-cluster" % Versions.dryad % Provided

    val grpc: Seq[ModuleID] = Seq(
      "io.grpc" % "grpc-stub" % Versions.grpc % Provided,
      "io.grpc" % "grpc-netty" % Versions.grpc % Provided,
      "io.grpc" % "grpc-services" % Versions.grpc % Provided,
      "io.grpc" % "grpc-protobuf" % Versions.grpc % Provided)

    val playframework = Seq(
      "com.typesafe.play" %% "play-guice" % Versions.play % Provided,
      "com.typesafe.play" %% "play-ws" % Versions.play % Provided,
      "com.typesafe.play" %% "play-server" % Versions.play % Provided)

    val log4j2 = Seq(
      "org.apache.logging.log4j" % "log4j-api" % Versions.log4j2 % Provided,
      "org.apache.logging.log4j" % "log4j-core" % Versions.log4j2 % Provided,
      "org.apache.logging.log4j" % "log4j-slf4j-impl" % Versions.log4j2 % Provided)

    val redis = Seq("com.github.etaty" %% "rediscala" % Versions.redis % Provided)

    val asynchttpclient = Seq("org.asynchttpclient" % "async-http-client" % Versions.httpclient)

    val json = Seq(
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % Versions.jackson % Provided)

    val urlParser = "io.mola.galimatias" % "galimatias" % Versions.urlParser

    val spring = Seq("org.springframework" % "spring-web" % Versions.spring % Provided)

    val slickPg = Seq(
      "com.github.tminglei" %% "slick-pg" % Versions.slickPg % Provided,
      "com.github.tminglei" %% "slick-pg_jts" % Versions.slickPg % Provided,
      "com.github.tminglei" %% "slick-pg_play-json" % Versions.slickPg % Provided)

    val playSlick = Seq(
      "com.typesafe.play" %% "play-slick" % Versions.playSlick % Provided,
      "com.typesafe.slick" %% "slick" % Versions.slick % Provided,
      "com.typesafe.play" %% "play-slick-evolutions" % Versions.playSlick % Provided) ++ slickPg

  }

  object Tests {
    val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % Versions.scalatest % Test
  }

  lazy val dependencies: Seq[ModuleID] =
    grpc ++ playframework ++ log4j2 ++ redis ++ asynchttpclient ++ spring ++
      playSlick ++
      json ++ Seq(dryad, urlParser, hashids, config, i18n, Tests.scalaTest)

}
