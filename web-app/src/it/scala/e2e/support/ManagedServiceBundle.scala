package e2e.support

import scala.util.Try

case class ManagedServiceBundle(mainService: ManagedService, collaborators: ManagedService*) extends ManagedService {

  override def start: Try[ManagedServiceBundle] = Try {
    collaborators.foreach(_.start.get)
    mainService.start.get
    this
  }

  override def stop: Try[ManagedServiceBundle] = Try {
    mainService.stop.get
    collaborators.foreach(_.stop.get)
    this
  }
}
