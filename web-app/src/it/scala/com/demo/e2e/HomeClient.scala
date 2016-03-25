package com.demo.e2e

import e2e.support.TestHttpClient
import org.springframework.http.ResponseEntity


class HomeClient(val port: Int) extends TestHttpClient {

  def getHome(params: Map[String, String] = Map.empty): ResponseEntity[String] = {
    get("/", params, classOf[String])
  }
}
