package env

import scala.util.Try

trait ManagedService {
  def start: Try[ManagedService]
  def stop: Try[ManagedService]
}
