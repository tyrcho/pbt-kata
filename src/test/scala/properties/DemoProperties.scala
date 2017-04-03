
package properties

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.PropertyChecks
import java.text.SimpleDateFormat
import java.util.Date
import org.scalacheck.Gen

@RunWith(classOf[JUnitRunner])
class DemoProperties extends FlatSpec with Matchers with PropertyChecks {

  val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS")
  def toString(d: Date) = dateFormat.format(d)
  def fromString(s: String) = dateFormat.parse(s)

  "reverse" should "be symmetric" in {
    forAll { l: List[Int] =>
      println(l)
      l.reverse.reverse shouldBe l
    }
  }

  "format and parse" should "be opposite" in {
    forAll(genDate) { d: Date =>
      val s = toString(d)
      println(s)
      fromString(s) shouldBe d
    }
  }

  val genDate: Gen[Date] =
    for {
      y <- Gen.chooseNum(-2000, 2000)
      m <- Gen.chooseNum(0, 11)
      d <- Gen.chooseNum(0, 30)
    } yield new Date(y, m, d)

}