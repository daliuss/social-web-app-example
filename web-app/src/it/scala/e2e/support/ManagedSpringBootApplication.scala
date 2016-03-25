package e2e.support

import com.example.web.service.spring.SpringConfig
import org.springframework.boot.SpringApplication

import scala.util.Try

class ManagedSpringBootApplication(port: Int, url: String) extends ManagedService {

  val app = new SpringApplication(classOf[SpringConfig])
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
      s"--server.port=$port",
      s"--server.url=$url"
    )
  }
}
