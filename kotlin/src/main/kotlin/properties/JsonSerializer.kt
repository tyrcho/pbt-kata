package properties

import com.fasterxml.jackson.databind.ObjectMapper

data class Address(var street: String, var town: String, var zip: Int) {
    constructor(): this("","",0)
}

data class Person(var name: String, var address: Address) {
    constructor(): this("",Address())
}

object JsonSerializer {
    fun toString(p: Person): String = ObjectMapper().writeValueAsString(p)

    fun fromString(s: String): Person = ObjectMapper().readValue(s, Person::class.java)
}
