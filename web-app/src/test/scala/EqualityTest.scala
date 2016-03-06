import org.specs2.mutable.SpecWithJUnit
import org.specs2.specification.Scope

class EqualityTest extends SpecWithJUnit {

  "Common sense" should {
    "confirm that 1 is equal to 1" in new Scope {
      1 mustEqual 1
    }
  }
}
