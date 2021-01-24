package com.demo.e2e

import com.example.web.api.LoggedInUser
import e2e.support.TestHttpClient
import org.springframework.http.ResponseEntity


class HomeClient(val port: Int) extends TestHttpClient {

  def getUser(params: Map[String, String] = Map.empty): ResponseEntity[LoggedInUser] = {
    get("/user", params, classOf[LoggedInUser])
  }
}
