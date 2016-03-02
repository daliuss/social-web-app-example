import env.{ManagedService, ManagedSpringBootApplication}

import scala.util.Try

object Environment {

  val mainServicePort = 9001

  lazy val environment = new Environment(
    new ManagedSpringBootApplication(mainServicePort)
  ).start

  def get: Try[ManagedService] = environment
}

class Environment(mainService: ManagedService, collaborators: ManagedService*) extends ManagedService {

  override def start: Try[Environment] = Try {
    collaborators.foreach(_.start.get)
    mainService.start.get
    this
  }

  override def stop: Try[Environment] = Try {
    mainService.stop.get
    collaborators.foreach(_.stop.get)
    this
  }
}
