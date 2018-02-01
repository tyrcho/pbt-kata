package maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleMathsTest {

@Test
void threeShouldBePositive() {
    assertTrue(SimpleMaths.isPositive(3));
}

@Test
void minusTwoShouldBePositive() {
    assertFalse(SimpleMaths.isPositive(-2));
}

}