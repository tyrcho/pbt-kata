package properties;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

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

}