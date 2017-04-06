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
		//TODO : finish the implementation
		person.name = gen().type(String.class).generate(random, status);
		return person;
	}

}
