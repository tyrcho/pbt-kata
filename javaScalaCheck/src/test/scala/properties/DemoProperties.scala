
package properties

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.PropertyChecks
import java.text.SimpleDateFormat
import java.util.Date
import org.scalacheck.Gen
import Addition._
import MyCollections._
import scala.collection.JavaConversions._

@RunWith(classOf[JUnitRunner])
class DemoProperties extends FlatSpec with Matchers with PropertyChecks {

  // examples
  "(a+b).length" should "be a.length + b.length" in {
    forAll { (s1: String, s2: String) =>
      (s1 + s2).length shouldBe (s1.length + s2.length)
    }
  }

  "addition" should "be commutative" in {
    forAll { (i: Int, j: Int) =>
      add(i, j) shouldBe add(j, i)
    }
  }

  it should "be associative" in {
    forAll { (i: Int, j: Int, k: Int) =>
      add(i, add(j, k)) shouldBe add(add(i, j), k)
    }
  }

  it should "have 0 as neutral" in {
    forAll { (i: Int) =>
      add(i, 0) shouldBe i
    }
  }

  // TODO (there and back again) : test that list.reverse.reverse == list
  "list.reverse.reverse" should "be same as list" in {
    forAll { (l: List[String]) =>
      reverse(reverse(l)) should contain theSameElementsInOrderAs l
    }
  }
  // TODO (there and back again) : test that list.reverse.reverse == list
  "list.sort.sort" should "be same as list.sort" in {
    forAll { (l: List[String]) =>
      sort(sort(l)) should contain theSameElementsInOrderAs sort(l)
    }
  }

  //
  //  // some issues with date ~1900
  //  @Property
  //  public void dateFormatParse(@InRange(min = "1920", max = "3000", format = "YYYY") Date d) throws ParseException {
  //    String s = Dates.toString(d);
  //    System.out.println(s);
  //    Date d2 = Dates.fromString(s);
  //    assertThat(d).isEqualTo(d2);
  //  }
  //
  //  @Property
  //  public void dateNextDay(@InRange(min = "1920", max = "3000", format = "YYYY") Date d) throws ParseException {
  //    String s = Dates.toString(d);
  //    assertThat(Dates.nextDayJava8(s)).isEqualTo(Dates.nextDay(s));
  //  }
  //
  //  @Property
  //  public void jsonSerialize(@From(PersonGenerator.class) Person p) throws IOException {
  //    String s = JsonSerializer.toString(p);
  //    System.out.println(s);
  //    Person p2 = JsonSerializer.fromString(s);
  //    assertThat(p2).isEqualTo(p);
  //  }
  //
  //  @Property
  //  public void dbInsertGet(@From(PersonGenerator.class) Person p) throws IOException {
  //    DummyDao dao = new DummyDao();
  //    dao.insert(p);
  //    assertThat(dao.get(p.name)).isEqualTo(p);
  //  }
  //
  //  @Property
  //  public void dbInsertIdempotent(@From(PersonGenerator.class) Person p) throws IOException {
  //    DummyDao dao = new DummyDao();
  //    dao.insert(p);
  //    dao.insert(p);
  //    assertThat(dao.get(p.name)).isEqualTo(p);
  //  }

  //  val genDate: Gen[Date] =
  //    for {
  //      y <- Gen.chooseNum(-200, 100)
  //      //      y <- Gen.chooseNum(-2000, 2000)
  //      m <- Gen.chooseNum(0, 11)
  //      d <- Gen.chooseNum(0, 30)
  //    } yield new Date(y, m, d)

}