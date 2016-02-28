package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

object DemoApplication extends App {

	override def main(args: Array[String]) {
		SpringApplication.run(classOf[SampleConfig], args:_*)
	}
}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class SampleConfig {

  @Bean def home = new Home
}

@Controller
class Home {

  @RequestMapping(Array("/"))
  @ResponseBody
  def home = "Hello"
}