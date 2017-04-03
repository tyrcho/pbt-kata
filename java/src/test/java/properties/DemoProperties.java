package properties;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@RunWith(JUnitQuickcheck.class)
public class DemoProperties {

	@Property
	public void concatenationLength(String s1, String s2) {
		assertThat(s1.length() + s2.length()).isEqualTo((s1 + s2).length());
	}

	@Property
	public void reverseList(List<Integer> list) {
		List<Integer> original = new ArrayList<Integer>(list);
		Collections.reverse(list);
		Collections.reverse(list);
		assertThat(list).containsExactlyElementsOf(original);
	}

	@Property
	public void sortIdempotent(List<Integer> list) {
		Collections.sort(list);
		List<Integer> sorted = new ArrayList<Integer>(list);
		Collections.sort(list);
		assertThat(list).containsExactlyElementsOf(sorted);
	}

	@Property
	public void jsonSerialize(@From(PersonGenerator.class) Person p) throws IOException {
		String s = JsonSerializer.toString(p);
		System.out.println(s);
		Person p2 = JsonSerializer.fromString(s);
		assertThat(p2).isEqualTo(p);
	}

}