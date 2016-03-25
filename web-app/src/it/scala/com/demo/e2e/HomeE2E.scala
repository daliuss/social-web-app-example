package com.demo.e2e

import e2e.E2ETest
import e2e.env.EmbeddedEnvironment
import e2e.support.matchers.HttpResponseMatchers
import org.specs2.specification.Scope

class HomeE2E extends E2ETest with HttpResponseMatchers {

  class Context extends Scope {
    val driver = new HomeClient(EmbeddedEnvironment.mainServicePort)

    val dummyAuthorizationUrl = EmbeddedEnvironment.mainServiceUrl + "?jwt="
  }

  "Home controller" should {
    "denies access when no authentication" in new Context {
      driver.getHome() must (beUnauthorizedWith(
        /("entity") / "options" /#0 /("tag" -> "dummy")
          and /("entity") / "options" /#0 /("url" -> dummyAuthorizationUrl)
          and /("isError" -> "true")
      ) and withJsonUTF8ContentType)
    }

    "allows authenticated access" in new Context {
      driver.getHome(params = Map("jwt" -> "")) must (beOKWith(
        /("entity" -> "Hello!")
      ) and withJsonUTF8ContentType)
    }
  }
}
