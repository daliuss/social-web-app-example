package e2e

import e2e.env.EmbeddedEnvironment
import e2e.support.GlobalTestEnvSupport
import org.specs2.matcher.JsonMatchers
import org.specs2.mutable.SpecWithJUnit

trait E2ETest extends SpecWithJUnit with JsonMatchers with GlobalTestEnvSupport {
  override def testEnvironment = EmbeddedEnvironment.global
}
