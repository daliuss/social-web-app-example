package com.example.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

@Controller
class HomeController {

  @RequestMapping(Array("/"))
  @ResponseBody
  def home = Response("Hello!")
}


private[controllers] case class Response(message: String)