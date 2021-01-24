package e2e.support

import org.specs2.execute.{AsResult, Error, Result}
import org.specs2.specification.AroundEach
import org.specs2.specification.core.SpecificationStructure

import scala.util.{Failure, Success, Try}

trait GlobalTestEnvSupport extends AroundEach {
  self: SpecificationStructure =>

  def testEnvironment: Try[ManagedService]

  final override def around[T: AsResult](t: => T): Result = {
    testEnvironment match {
      case Success(_) => AsResult(t)
      case Failure(t) =>
        t.printStackTrace()
        Error("Failed to initialize test environment")
      case _ => Error(s"Invalid state $testEnvironment during test execution")
    }
  }
}