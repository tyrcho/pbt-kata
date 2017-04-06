package properties

import com.fasterxml.jackson.databind.ObjectMapper

data class Address(val street: String, val town: String, val zip: Int)
data class Person(var name: String, var address: Address) {
   constructor() :this("",Address("","",0))
}

object JsonSerializer {
    fun toString(p: Person) = ObjectMapper().writeValueAsString(p)

    fun fromString(s: String) = ObjectMapper().readValue(s, Person::class.java)
}
