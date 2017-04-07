package properties

import com.pholser.junit.quickcheck.From
import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.generator.InRange
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import jdk.nashorn.internal.runtime.JSONFunctions
import org.junit.runner.RunWith

import java.io.IOException
import java.text.ParseException
import java.util.ArrayList
import java.util.Collections
import java.util.Date
import org.assertj.core.api.Assertions.*

@RunWith(JUnitQuickcheck::class)
class DemoProperties {

    // example
    @Property
    fun concatenationLength(s1: String, s2: String) {
        assertThat(s1.length + s2.length).isEqualTo((s1 + s2).length)
    }

    // (there and back again) : test that list.reverse.reverse == list
    @Property
    fun reverseBackAgain(l: List<String>) {
        assertThat(l.reversed().reversed()).isEqualTo(l)
    }

    // (idempotence) : test that list.sort.sort == list.sort
    @Property
    fun sortIdempotence(l: List<String>) {
        assertThat(l.sorted().sorted()).isEqualTo(l.sorted())
    }

    // (symmetry) : for any date d, Dates.toString(d) is the
    // opposite of Dates.fromString(s)
    @Property
    fun dateSymmetry(d: Date) {
        assertThat(d).isEqualTo(Dates.fromString(Dates.toString(d)))
    }

    // Hint : public void dateFormatParse(@InRange(min = "1000", max = "2100",
    // format = "YYYY") Date d)

    // (equivalent implementations) : Dates.nextDayJava8(s) ==
    // Dates.nextDay(s)
    @Property
    fun dateEquivalentImplementations(@InRange(min="1000", max = "2100", format="YYYY") d:Date) {
        assertThat(Dates.nextDay(Dates.toString(d))).isEqualTo(Dates.nextDayJava8(Dates.toString(d)))
    }

    // (symmetry) : JsonSerializer.toString(person) and
    // JsonSerializer.fromString(s)
    // Hint : @From(PersonGenerator.class)
    @Property
    fun jsonSymmetry(@From(PersonGenerator::class) p: Person) {
        assertThat(p).isEqualTo(JsonSerializer.fromString(JsonSerializer.toString(p)))
    }

    // (symmetry) : DummyDao.insert(person) and dao.get(name)
    @Property
    fun daoSymmetry(@From(PersonGenerator::class) p: Person) {
        DummyDao.insert(p)
        assertThat(p).isEqualTo(DummyDao[p.name])
    }

    // (idempotence) : DummyDao.insert(person) has same effect when called
    // twice
    @Property
    fun daoIdempotence(@From(PersonGenerator::class) p: Person) {
        val before = DummyDao.size()
        (0..10).forEach { DummyDao.insert(p) }
        assertThat(DummyDao.size()).isEqualTo(before+1)
    }

}