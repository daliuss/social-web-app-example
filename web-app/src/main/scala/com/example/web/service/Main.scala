package com.example.web.service

import com.example.web.service.spring.SpringConfig
import org.springframework.boot.SpringApplication

object Main extends App {
  SpringApplication.run(classOf[SpringConfig], args: _*)
}
