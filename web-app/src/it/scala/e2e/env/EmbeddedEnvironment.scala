package e2e.env

import e2e.support.{ManagedServiceBundle, ManagedSpringBootApplication}

object EmbeddedEnvironment {

  val mainServicePort = 9001

  lazy val global = ManagedServiceBundle(
    new ManagedSpringBootApplication(mainServicePort)
  ).start

}
