package properties;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static properties.Addition.add;
import static properties.MyCollections.reverse;
import static properties.MyCollections.sort;

@RunWith(JUnitQuickcheck.class)
public class DemoProperties {
    // examples
    @Property
    public void concatenationLength(String s1, String s2) {
        assertThat(s1.length() + s2.length()).isEqualTo((s1 + s2).length());
    }

    @Property
    public void additionAssoc(@InRange(min = "-10", max = "10") int i, int j, int k) {
        assertThat(add(add(i, j), k)).isEqualTo(add(i, add(j, k)));
    }

    @Property
    public void additionCommu(int i, int j, int k) {
        assertThat(add(i, j)).isEqualTo(add(j, i));
    }

    @Property
    public void additionNeutral(int i) {
        assertThat(add(i, 0)).isEqualTo(i);
    }

    // TODO (there and back again) : test that list.reverse.reverse == list
    @Property
    public void reverseList(List<Integer> list) {
        List<Integer> twice = reverse(reverse(list));
        assertThat(twice).containsExactlyElementsOf(list);
    }

    // TODO (idempotence) : test that list.sort.sort == list.sort
    @Property
    public void sortIdempotent(List<Integer> list) {
        List<Integer> twice = sort(sort(list));
        assertThat(twice).containsExactlyElementsOf(sort(list));
    }

    // some issues with date ~1900
    @Property
    public void dateFormatParse(@InRange(min = "1920", max = "3000", format = "YYYY") Date d) throws ParseException {
        String s = Dates.toString(d);
        System.out.println(s);
        Date d2 = Dates.fromString(s);
        assertThat(d).isEqualTo(d2);
    }

    @Property
    public void dateNextDay(@InRange(min = "1920", max = "3000", format = "YYYY") Date d) throws ParseException {
        String s = Dates.toString(d);
        assertThat(Dates.nextDayJava8(s)).isEqualTo(Dates.nextDay(s));
    }

    // Does not work with min date 1500, probably because of Julian / Gregorian change
    @Property
    public void dayOfWeek(@InRange(min = "1583", max = "3000") int y,
                           @InRange(min = JANUARY + "", max = DECEMBER + "") int m,
                           @InRange(min = "1", max = "31") int d) {
        assertThat(Problem1Calendar.fastDayOfWeek(y, m, d))
                .withFailMessage("%s/%s/%s", d, m, y)
                .isEqualTo(Problem1Calendar.dayOfWeek(y, m, d));
    }

    @Property
    public void jsonSerialize(@From(PersonGenerator.class) Person p) throws IOException {
        String s = JsonSerializer.toString(p);
        System.out.println(s);
        Person p2 = JsonSerializer.fromString(s);
        assertThat(p2).isEqualTo(p);
    }

    @Property
    public void dbInsertGet(@From(PersonGenerator.class) Person p) throws IOException {
        DummyDao dao = new DummyDao();
        dao.insert(p);
        assertThat(dao.get(p.name)).isEqualTo(p);
    }

    @Property
    public void dbInsertIdempotent(@From(PersonGenerator.class) Person p) throws IOException {
        DummyDao dao = new DummyDao();
        dao.insert(p);
        dao.insert(p);
        assertThat(dao.get(p.name)).isEqualTo(p);
    }

}