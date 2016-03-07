package com.example.web.service.spring

import com.example.web.controllers.HomeController
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
@EnableAutoConfiguration
@ComponentScan
class SpringConfig {

  @Bean
  def objectMapper(builder: Jackson2ObjectMapperBuilder) = {
    val om = new ObjectMapper
    om.registerModule(DefaultScalaModule)
    om
  }

  @Bean def home = new HomeController
}
