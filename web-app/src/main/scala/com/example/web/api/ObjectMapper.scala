package com.example.web.api

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object ObjectMapper {
  val default = JsonMapper.builder()
    .addModule(DefaultScalaModule)
    .build()
}
