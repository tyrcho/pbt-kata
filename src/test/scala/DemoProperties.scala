
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.PropertyChecks
import org.scalacheck.Gen
import org.scalacheck.Arbitrary

@RunWith(classOf[JUnitRunner])
class DemoProperties extends FlatSpec with Matchers with PropertyChecks {

  "reverse" should "be symmetric" in {
    forAll { l: List[Int] =>
      println(l)
      l.reverse.reverse shouldBe l
    }
  }

}