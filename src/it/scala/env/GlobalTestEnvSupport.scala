package env

import java.io.{PrintWriter, StringWriter}

import org.specs2.execute.{AsResult, Error, Result}
import org.specs2.specification.AroundEach
import org.specs2.specification.core.SpecificationStructure

import scala.util.{Failure, Success, Try}

trait GlobalTestEnvSupport extends AroundEach {
  self: SpecificationStructure =>

  def testEnv: Try[ManagedService]


  final override def around[T: AsResult](t: => T): Result = {
    testEnv match {
      case Success(_) => AsResult(t)
      case Failure(_) => Error("Failed to initialize test environment")
      case _ => Error(s"Invalid state $testEnv during test execution")
    }
  }

  private def printEnvironmentInitError(t: Throwable): Unit = {
    println("-------------------------------------------------------------------")
    println(s"Environment failed. Reason: ${t.getMessage}")

    val stringWriter = new StringWriter
    t.printStackTrace(new PrintWriter(stringWriter))
    println(stringWriter.toString)
  }
}