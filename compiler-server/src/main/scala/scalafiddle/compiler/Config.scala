package scalafiddle.compiler

import java.util.Properties

import com.typesafe.config.ConfigFactory

case class Template(pre: String, post: String) {
  def fullSource(src: String) = pre + src + post
}

object Config {
  protected val config = ConfigFactory.load().getConfig("fiddle")

  val interface = config.getString("interface")
  val port      = config.getInt("port")
  val routerUrl = config.getString("routerUrl")
  val secret    = config.getString("secret")

  val libCache          = config.getString("libCache")
  val compilerCacheSize = config.getInt("compilerCacheSize")

  // read the generated version data
  protected val versionProps = new Properties()
  versionProps.load(getClass.getResourceAsStream("/version.properties"))

  val version          = versionProps.getProperty("version")
  val scalaVersion     = versionProps.getProperty("scalaVersion")
  val scalaMainVersion = scalaVersion.split('.').take(2).mkString(".")
  val scalaJSVersion   = versionProps.getProperty("scalaJSVersion")
  val aceVersion       = versionProps.getProperty("aceVersion")

  val scalaJSBinVersion = {
    /* This assumes that we don't use Scala.js 2.x or something. If it happens
     * in the very far future, we can change this.
     */
    if (scalaJSVersion.startsWith("0.6."))
      "0.6"
    else
      "1"
  }

}
