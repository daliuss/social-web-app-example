package com.example.web.controllers

import com.example.web.service.Config
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

@Controller
class HomeController(val config: Config) extends BaseController {

  @RequestMapping(Array("/"))
  @ResponseBody
  def home(@RequestParam(required = false) jwt: String) = secured(jwt) {
    Response("Hello!")
  }
}
