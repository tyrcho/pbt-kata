package properties

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import java.util.*

class DemoPropertiesKotlinTest : StringSpec() {
    init {
        "String size" {
            forAll { a: String, b: String ->
                (a + b).length == a.length + b.length
            }
        }
        "List reverse" {
            forAll {
                l: List<String> ->
                l.reversed().reversed() == l
            }
        }
        "Sort idempotence" {
            forAll {
                l: List<String> ->
                l.sorted().sorted() == l.sorted()
            }
        }
        "Date Symmetry" {
            forAll(DateGenerator()) {
                d: Date ->
                d == Dates.fromString(Dates.toString(d))
            }
        }
        "Date Addition Comparison" {
            forAll(DateGenerator()) {
                d: Date ->
                Dates.nextDay(Dates.toString(d)) == Dates.nextDayJava8(Dates.toString(d))
            }
        }
        "JSon Serialize and Deserialize" {
            forAll(PersonGenerator2()) {
                p: Person ->
                p == JsonSerializer.fromString(JsonSerializer.toString(p))
            }
        }
        "DAO Symmetry" {
            forAll(PersonGenerator2()) {
                p: Person ->
                DummyDao.insert(p)
                p == DummyDao[p.name]

            }
        }
        "DAO Idempotence" {
            forAll(PersonGenerator2()) {
                p: Person ->
                val before = DummyDao.size()
                (0..10).forEach { DummyDao.insert(p) }
                before + 1 == DummyDao.size()
            }
        }
    }
}

class DateGenerator : Gen<Date> {
    override fun generate(): Date = Date(Gen.choose(1000, 2100).generate(),
                                         Gen.choose(0, 11).generate(),
                                         Gen.choose(0, 30).generate())
}

class PersonGenerator2 : Gen<Person> {
    override fun generate(): Person = Person(name = Gen.string().nextPrintableString(10),
                                             address = Address())
}