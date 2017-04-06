package properties;

import java.util.HashMap;
import java.util.Map;

public class DummyDao {
	private Map<String, Person> mockDb = new HashMap<String, Person>();

	public  void insert(Person p) {
		mockDb.put(p.name, p);
	}

	public Person get(String name) {
		return mockDb.get(name);
	}
}
