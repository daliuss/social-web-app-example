package com.example.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

@Controller
class HomeController extends BaseController {

  @RequestMapping(Array("/"))
  @ResponseBody
  def home(@RequestParam(required = false) authenticate: String) = secured(authenticate) {
    Response("Hello!")
  }
}
