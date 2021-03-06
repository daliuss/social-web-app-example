package com.example.web.controllers

import com.example.web.api.LoggedInUser
import com.example.web.service.Config
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.{GetMapping, _}

@RestController
class HomeController(val config: Config) extends BaseController {

  @GetMapping(value = Array("/user"), produces = Array("application/json;charset=utf-8"))
  def user(@AuthenticationPrincipal principal: OAuth2User): LoggedInUser = {
    LoggedInUserResolver.resolve(principal)
  }

}




