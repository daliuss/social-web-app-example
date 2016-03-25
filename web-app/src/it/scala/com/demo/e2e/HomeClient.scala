package com.demo.e2e

import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.ResponseEntity

class HomeClient(port: Int) {

  def getHome: ResponseEntity[String] = get("/", classOf[String])

  def get[T](path: String, t: Class[T]): ResponseEntity[T] = {
    new TestRestTemplate().getForEntity(s"http://localhost:$port/$path", t)
  }
}
