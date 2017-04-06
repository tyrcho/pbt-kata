package properties;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@RunWith(JUnitQuickcheck.class)
public class DemoProperties {

	// example
	@Property
	public void concatenationLength(String s1, String s2) {
		assertThat(s1.length() + s2.length()).isEqualTo((s1 + s2).length());
	}

	// TODO (there and back again) : test that list.reverse.reverse == list

	// TODO (idempotence) : test that list.sort.sort == list.sort

	// TODO (symmetry) : for any date d, Dates.toString(d) is the
	// opposite of Dates.fromString(s)

	// Hint : public void dateFormatParse(@InRange(min = "1000", max = "2100",
	// format = "YYYY") Date d)

	// TODO (equivalent implementations) : Dates.nextDayJava8(s) ==
	// Dates.nextDay(s)

	// TODO (symmetry) : JsonSerializer.toString(person) and
	// JsonSerializer.fromString(s)
	// Hint : @From(PersonGenerator.class)

	// TODO (symmetry) : DummyDao.insert(person) and dao.get(name)

	// TODO (idempotence) : DummyDao.insert(person) has same effect when called
	// twice

}