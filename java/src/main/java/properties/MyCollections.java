package properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyCollections {
	static <T> List<T> reverse(List<T> list) {
		ArrayList<T> copy = new ArrayList<>(list);
		Collections.reverse(copy); // buggy !
		return copy;
	}

	static <T extends Comparable<T>> List<T> sort(List<T> list) {
		ArrayList<T> copy = new ArrayList<>(list);
		Collections.sort(copy); // buggy !
		return copy;
	}
}
