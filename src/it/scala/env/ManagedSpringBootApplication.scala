package env

import com.example.SampleConfig
import org.springframework.boot.SpringApplication

import scala.util.Try

class ManagedSpringBootApplication(port: Int) extends ManagedService {

  val app = new SpringApplication(classOf[SampleConfig])
  lazy val instance = app.run(buildArgumentList:_*)

  override def start: Try[ManagedService] = Try {
    if (!instance.isActive) {
      throw new IllegalStateException("Failed to start Spring Boot app")
    }
    this
  }

  override def stop: Try[ManagedService] = Try {
    instance.close()
    this
  }

  private[this] def buildArgumentList: Array[String] = {
    Array(
      s"--server.port=$port"
    )
  }
}
