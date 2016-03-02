import env.GlobalTestEnvSupport
import org.specs2.mutable.SpecWithJUnit

trait E2ETest extends SpecWithJUnit with GlobalTestEnvSupport {
  override def testEnv = Environment.get
}
