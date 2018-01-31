package properties;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;

import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static properties.MyCollections.reverse;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;
import static org.quicktheories.generators.Generate.*;
import static properties.Addition.add;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TheoriesTest {

    @Test
    public void sampleProperty() {
        qt()
                .forAll(range(1, 102), constant(7))
                .check((i, c) -> i + c >= 7);
    }

    @Test
    public void additionAssoc() {
        qt()
                .forAll(integers().all(), integers().all(), integers().all())
                .check((i, j, k) -> add(add(i, j), k) == (add(i, add(j, k))));
    }

    @Test
    public void additionCommu() {
        qt()
                .forAll(integers().all(), integers().all())
                .check((i, j) -> add(i, j) == add(j, i));
    }


    @Test
    public void additionNeutral() {
        qt()
                .forAll(integers().all())
                .check(i -> add(i, 0) == i);
    }


    // TODO (there and back again) : test that list.reverse.reverse == list
    @Test
    public void reverseList() {
        qt()
                .forAll(lists().of(integers().all()).ofSizeBetween(0, 100))
                .check(l -> reverse(reverse(l)).equals(l));
    }

    // Does not work with min date 1500, probably because of Julian / Gregorian change
    @Test
    public void dayOfWeek() {
        qt()
                .forAll(integers().between(1583, 3000), integers().between(JANUARY, DECEMBER), integers().between(1, 31))
                .check((y, m, d) -> Problem1Calendar.fastDayOfWeek(y, m, d) == Problem1Calendar.dayOfWeek(y, m, d));
    }
}
