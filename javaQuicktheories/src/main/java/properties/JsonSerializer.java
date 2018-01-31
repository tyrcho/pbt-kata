package properties;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer {
	public static String toString(Person p) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(p);
	}
	
	public static Person fromString(String s) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(s, Person.class);
	}
}
