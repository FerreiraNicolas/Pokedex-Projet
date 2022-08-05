name := "PokemonDB"
 
version := "0.1"
 
scalaVersion := "3.1.3"
 
libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.12",
  "org.scalatest" %% "scalatest" % "3.2.12" % "test",
  "io.monix" %% "monix" % "3.4.0",
  "io.monix" %% "monix-eval" % "3.4.0",
  "io.spray" %%  "spray-json" % "1.3.6",
  "com.squareup.okhttp3" % "okhttp" % "4.10.0",
)