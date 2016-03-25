package e2e.support

import org.slf4j.LoggerFactory
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.util.UriComponentsBuilder

import scala.collection.JavaConversions._

trait TestHttpClient {

  def port: Int

  val logger = LoggerFactory.getLogger(getClass)

  def get[T](path: String, params: Map[String, String], entityClass: Class[T]): ResponseEntity[T] = {
    val multiMap = new LinkedMultiValueMap[String, String]()
    multiMap.setAll(params)
    val uri = UriComponentsBuilder.fromHttpUrl(s"http://localhost:$port/$path").queryParams(multiMap).toUriString
    logger.info(s"GET $uri")
    new TestRestTemplate().getForEntity(uri, entityClass)
  }
}
