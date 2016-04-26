import sbtassembly.AssemblyKeys
// put this at the top of the file

assemblyJarName in assembly := "TwitterStream.jar"

name := "TwitterStream"
mainClass in assembly := Some("com.learn.TwitterStream")

version := "1.0"
scalaVersion := "2.10.6"


javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

// protocol buffer support
// Seq(sbtprotobuf.ProtobufPlugin.protobufSettings: _*)

// additional libraries
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.0" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.6.0",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.0",
  "org.apache.commons" % "commons-lang3" % "3.0",
  "org.apache.spark" %% "spark-streaming-twitter" % "1.6.0",
  "org.apache.spark" % "spark-sql_2.10" % "1.6.0",
  "com.google.code.gson" % "gson" % "2.3.1"

)

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last

  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case "application.conf"                            => MergeStrategy.concat
  case "unwanted.txt"                                => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
  case _ => MergeStrategy.first

}
