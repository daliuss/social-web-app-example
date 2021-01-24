package e2e.support

import com.example.web.api.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.util.UriComponentsBuilder

import scala.jdk.CollectionConverters._

trait TestHttpClient {

  def port: Int

  val logger = LoggerFactory.getLogger(getClass)

  val template = new TestRestTemplate()

  template.getRestTemplate.getMessageConverters.add(0, createMappingJacksonHttpMessageConverter())

  def get[T](path: String, params: Map[String, String], entityClass: Class[T]): ResponseEntity[T] = {
    val multiMap = new LinkedMultiValueMap[String, String]()
    multiMap.setAll(params.asJava)
    val uri = UriComponentsBuilder.fromHttpUrl(s"http://localhost:$port/$path").queryParams(multiMap).toUriString
    logger.info(s"GET $uri")
    template.getForEntity(uri, entityClass)
  }

  private def createMappingJacksonHttpMessageConverter(): HttpMessageConverter[_] = {
    val converter = new MappingJackson2HttpMessageConverter
    converter.setObjectMapper(ObjectMapper.default)
    converter
  }

}
