package com.demo.e2e

import e2e.E2ETest
import org.specs2.specification.Scope
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

class HttpErrorE2E extends E2ETest {

  class Context extends Scope

  "Dispatcher" should {
    "return not found" in new Context {
      val entity = new TestRestTemplate().getForEntity("http://localhost:" + 9001 + "/index", classOf[String])
      entity.getStatusCode mustEqual HttpStatus.NOT_FOUND
    }
  }
}
