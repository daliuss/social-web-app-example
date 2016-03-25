package com.demo.e2e

import e2e.E2ETest
import e2e.env.EmbeddedEnvironment
import e2e.support.matchers.HttpResponseMatchers
import org.specs2.specification.Scope

class HomeE2E extends E2ETest with HttpResponseMatchers {

  class Context extends Scope {
    val driver = new HomeClient(EmbeddedEnvironment.mainServicePort)
  }

  "Home controller" should {
    "return Hello" in new Context {
      driver.getHome must beOKWith(
        /("message" -> "Hello!")
      ).and(withJsonUTF8ContentType)
    }
  }
}
