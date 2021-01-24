package e2e.support.matchers

import org.specs2.matcher.Matcher
import org.specs2.mutable.Spec
import org.springframework.http.{HttpStatus, ResponseEntity}

import scala.jdk.CollectionConverters._

trait HttpResponseMatchers extends Spec {

  def withHttpStatus[T](status: HttpStatus): Matcher[ResponseEntity[T]] = {
    beEqualTo(status) ^^ {
      (_: ResponseEntity[T]).getStatusCode
    }
  }

  def withJsonUTF8ContentType[T]: Matcher[ResponseEntity[T]] = {
    contain[String]("application/json;charset=utf-8") ^^ {
      (_: ResponseEntity[T]).getHeaders.get("Content-Type").asScala
    }
  }

  def matchResponseBody[T](matcher: Matcher[T]): Matcher[ResponseEntity[T]] = {
    matcher ^^ {
      (_: ResponseEntity[T]).getBody
    }
  }

  def beOKWith[T](matcher: Matcher[T]): Matcher[ResponseEntity[T]] = {
    withHttpStatus[T](HttpStatus.OK) and matchResponseBody[T](matcher)
  }

  def beUnauthorizedWith[T](matcher: Matcher[T]): Matcher[ResponseEntity[T]] = {
    withHttpStatus[T](HttpStatus.UNAUTHORIZED) and matchResponseBody[T](matcher)
  }

}
