package com.example.web.controllers

import java.net.URL

import com.example.web.exceptions.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{ResponseBody, ResponseStatus, ExceptionHandler}

trait BaseController {

  def secured(jwt: String)(execution: =>Response) = {
    if (jwt != null) {
      execution
    } else {
      throw new UnauthorizedException
    }
  }

  @ExceptionHandler(Array(classOf[UnauthorizedException]))
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  def handleException(exception: UnauthorizedException) = {
    val options = AuthOptions(AuthOption("dummy", new URL("http://localhost")) :: Nil)
    Response(options, isError = true)
  }
}

private[controllers] case class Response(entity: AnyRef, isError: Boolean = false)

private[controllers] case class AuthOptions(options: List[AuthOption])

private[controllers] case class AuthOption(tag: String, url: URL)
