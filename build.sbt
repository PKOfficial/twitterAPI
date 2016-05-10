name := "twitterAPI"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq("org.twitter4j" % "twitter4j-core" % "4.0.4",
  "com.typesafe" % "config" % "1.2.1",
  "junit" % "junit" % "4.11" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5")
    