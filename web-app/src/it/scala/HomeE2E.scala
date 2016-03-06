import org.specs2.specification.Scope
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.HttpStatus

class HomeE2E extends E2ETest {

  class Context extends Scope

  "Home controller" should {
    "return Hello" in new Context {
      val entity = new TestRestTemplate().getForEntity("http://localhost:" + 9001, classOf[String])
      println(entity)
      entity.getStatusCode mustEqual HttpStatus.OK
      entity.getBody mustEqual "Hello"
    }
  }
}
