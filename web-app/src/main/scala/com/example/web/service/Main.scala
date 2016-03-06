package com.example.web.service

import com.example.web.service.spring.SpringConfig
import org.springframework.boot.SpringApplication

object Main extends App {

	override def main(args: Array[String]) {
		SpringApplication.run(classOf[SpringConfig], args:_*)
	}
}
