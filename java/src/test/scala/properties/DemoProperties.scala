
package properties

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.PropertyChecks
import java.text.SimpleDateFormat
import java.util.Date
import org.scalacheck.Gen
import org.scalacheck.Shapeless._

@RunWith(classOf[JUnitRunner])
class DemoProperties extends FlatSpec with Matchers with PropertyChecks {

  "reverse" should "be symmetric" in {
    forAll { l: List[Int] =>
      println(l)
      l.reverse.reverse shouldBe l
    }
  }

  "sort" should "be idempotent" in {
    forAll { l: List[Int] =>
      println(l)
      l.sorted.sorted shouldBe l.sorted
    }
  }

  "json : format and parse" should "be opposite" in {
    forAll { p: Person =>
      val s = JsonSerialization.toString(p)
      println(s)
      JsonSerialization.fromString(s) shouldBe p
    }
  }

  "dateformat : format and parse" should "be opposite" in {
    forAll(genDate) { d: Date =>
      val s = Dates.toString(d)
      println(s)
      Dates.fromString(s) shouldBe d
    }
  }

  "nextDay : java and joda" should "be the same" in {
    forAll(genDate) { d: Date =>
      val s = Dates.toString(d)
      println(s)

      Dates.nextDay(s) shouldBe Dates.nextDayJoda(s)
    }
  }

  "db : insert and get" should "be symmetric" in {
    forAll { p: Person =>
      DummyDao.insert(p.name, p)
      DummyDao.get(p.name) shouldBe Some(p)
    }
  }

  "db : insert" should "be idempotent" in {
    forAll { p: Person =>
      DummyDao.insert(p.name, p)
      DummyDao.insert(p.name, p)
      DummyDao.get(p.name) shouldBe Some(p)
    }
  }

  val genDate: Gen[Date] =
    for {
      y <- Gen.chooseNum(-200, 100)
      //      y <- Gen.chooseNum(-2000, 2000)
      m <- Gen.chooseNum(0, 11)
      d <- Gen.chooseNum(0, 30)
    } yield new Date(y, m, d)

}