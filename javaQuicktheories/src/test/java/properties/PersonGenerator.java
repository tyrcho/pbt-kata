package properties;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class PersonGenerator extends Generator<Person> {

    public PersonGenerator() {
        super(Person.class);
    }

    public Person generate(SourceOfRandomness random, GenerationStatus status) {
        Person person = new Person();
        person.address = new Address();
        person.address.street = gen().type(String.class).generate(random, status);
        person.address.zipcode = gen().type(Integer.class).generate(random, status);
        person.name = gen().type(String.class).generate(random, status);
        return person;
    }

}
