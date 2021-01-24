package com.demo.e2e

import com.example.web.api.LoggedInUser
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

    "allows authenticated access" in new Context {
      driver.getUser() must (beOKWith[LoggedInUser](
        beEqualTo(LoggedInUser("Test user", "test@test"))
      ) and withJsonUTF8ContentType[LoggedInUser])
    }
  }
}
