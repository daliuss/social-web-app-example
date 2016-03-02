package env

import java.io.{PrintWriter, StringWriter}

import org.specs2.execute.{AsResult, Error, Result}
import org.specs2.specification.AroundEach
import org.specs2.specification.core.{Description, Fragments, SpecificationStructure}
import org.specs2.specification.create.FragmentsFactory

import scala.util.{Try, Success, Failure}

trait GlobalTestEnvSupport extends AroundEach with BeforeAllWithNamedStep {
  self: SpecificationStructure =>

  def testEnv: Try[ManagedService]

  final override val beforeAllStepName = "Step - Env Setup"

  final override def beforeAll: Unit = {
    testEnv match {
      case Failure(e) => printEnvironmentInitError(e)
      case _ =>
    }
  }

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

private[env] trait BeforeAllWithNamedStep extends SpecificationStructure with FragmentsFactory {
  def beforeAll: Unit

  def beforeAllStepName: String

  override def map(fs: => Fragments) = {
    val beforeAllFragment = fragmentFactory.step(beforeAll).copy(description = Description.text(beforeAllStepName))
    super.map(fs).prepend(beforeAllFragment)
  }
}