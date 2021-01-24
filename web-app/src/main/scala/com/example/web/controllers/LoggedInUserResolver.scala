package com.example.web.controllers

import com.example.web.api.LoggedInUser
import org.springframework.security.oauth2.core.user.OAuth2User

object LoggedInUserResolver {

  def resolve(principal: OAuth2User): LoggedInUser = {
    Option(principal) match {
      case Some(p) => LoggedInUser(
        name = p.getAttribute("name"),
        email = p.getAttribute("email"),
      )
      case _ => LoggedInUser("Test user", "test@test")
    }
  }

}


