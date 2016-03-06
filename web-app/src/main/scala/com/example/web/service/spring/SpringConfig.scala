package com.example.web.service.spring

import com.example.web.controllers.HomeController
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class SpringConfig {

  @Bean def home = new HomeController
}
