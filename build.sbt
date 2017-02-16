
name := "spark-statsd"

version := "1.0.0"

organization := "spark.metrics"

scalaVersion := "2.11.8"

sparkVersion := "2.0.2"

//sparkComponents += "core"

resolvers += "Bintray" at "https://dl.bintray.com/readytalk/maven"

libraryDependencies += "com.readytalk" % "metrics3-statsd" % "4.1.0"

assemblyJarName in assembly := s"${name.value}-${version.value}.jar"

assemblyMergeStrategy in assembly := {
      case "BUILD" => MergeStrategy.discard
      case other => MergeStrategy.defaultMergeStrategy(other)
      case PathList("META-INF", xs @ _*) =>
        (xs map {_.toLowerCase}) match {
          case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) =>
            MergeStrategy.discard
          case ps @ (x :: xs) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
            MergeStrategy.discard
          case "plexus" :: xs =>
            MergeStrategy.discard
          case "services" :: xs =>
            MergeStrategy.filterDistinctLines
          case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) =>
            MergeStrategy.filterDistinctLines
          case _ => MergeStrategy.deduplicate
        }
    }
