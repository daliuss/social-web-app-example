package e2e.env

import e2e.support.{ManagedServiceBundle, ManagedSpringBootApplication}

object EmbeddedEnvironment {

  val mainServicePort = 9001

  val mainServiceUrl = s"http://localhost:$mainServicePort"

  lazy val global = ManagedServiceBundle(
    new ManagedSpringBootApplication(mainServicePort, mainServiceUrl)
  ).start

}
