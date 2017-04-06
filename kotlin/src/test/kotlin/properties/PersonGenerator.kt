package properties

import com.pholser.junit.quickcheck.generator.GenerationStatus
import com.pholser.junit.quickcheck.generator.Generator
import com.pholser.junit.quickcheck.random.SourceOfRandomness

class PersonGenerator : Generator<Person>(Person::class.java) {

    override fun generate(random: SourceOfRandomness, status: GenerationStatus): Person {
        val person = Person()
        //TODO : finish the implementation
        person.name = gen().type(String::class.java).generate(random, status)
        return person
    }

}
