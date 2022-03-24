package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

class PriceRangeTest {

    private PriceRange editablePriceRange = new PriceRange(10, 20);


    @Test
    public void isValidPriceRangeTest() {
        //lower higher than upper.
        assertFalse(PriceRange.isValidPriceRange(20, 10));

        //negative values.
        assertFalse(PriceRange.isValidPriceRange(-10, 50));
        assertFalse(PriceRange.isValidPriceRange(-100, -50));

        //inclusivity is fine.
        assertTrue(PriceRange.isValidPriceRange(10, 10)); //lower can equal upper.

        //positive test cases.
        assertTrue(PriceRange.isValidPriceRange(0, 100));
        assertTrue(PriceRange.isValidPriceRange(1, 2));
    }

    @Test
    public void priceRangeToStringTest() {
        PriceRange pr1 = new PriceRange(0, 100);
        PriceRange pr2 = new PriceRange(50, 100);
        assertEquals("[0,100]", pr1.toString());
        assertEquals("[50,100]", pr2.toString());
    }

    @Test
    public void getLowerTest() {
        PriceRange pr1 = new PriceRange(0, 100);
        PriceRange pr2 = new PriceRange(50, 100);
        assertEquals(0, pr1.getLower());
        assertEquals(50, pr2.getLower());
    }

    @Test
    public void getUpperTest() {
        PriceRange pr1 = new PriceRange(0, 100);
        PriceRange pr2 = new PriceRange(50, 100);
        assertEquals(100, pr1.getUpper());
        assertEquals(100, pr2.getUpper());
    }

    @Test
    public void isWithinRangeTest() throws IllegalValueException {
        PriceRange pr1 = new PriceRange(45, 100);
        PriceRange pr2 = new PriceRange(50, 150);
        PriceRange pr4 = new PriceRange(44, 46);

        //within range
        assertTrue(PriceRange.isWithinRange(90, pr1));
        assertTrue(PriceRange.isWithinRange(150, pr2)); //inclusive
        assertTrue(PriceRange.isWithinRange(50, pr2)); //inclusive
        assertTrue(PriceRange.isWithinRange(45, pr4));

        //outside range
        assertFalse(PriceRange.isWithinRange(30, pr1));
        assertFalse(PriceRange.isWithinRange(-5, pr1));
        assertFalse(PriceRange.isWithinRange(43, pr4));
        assertFalse(PriceRange.isWithinRange(47, pr4));
    }

    @Test
    public void canMatchPriceTest() throws IllegalValueException {
        PriceRange pr1 = new PriceRange(45, 100);
        PriceRange pr2 = new PriceRange(50, 150);
        PriceRange pr3 = new PriceRange(101, 150);
        PriceRange pr4 = new PriceRange(44, 46);

        assertTrue(PriceRange.canMatchPrice(pr1, pr2)); //values from 50-100 are valid matches
        assertTrue(PriceRange.canMatchPrice(pr2, pr1)); //other way should work also

        assertTrue(PriceRange.canMatchPrice(pr3, pr2));
        assertTrue(PriceRange.canMatchPrice(pr2, pr3));

        assertTrue(PriceRange.canMatchPrice(pr4, pr1));
        assertTrue(PriceRange.canMatchPrice(pr1, pr4)); // other way should be the same

        assertFalse(PriceRange.canMatchPrice(pr1, pr3));
        assertFalse(PriceRange.canMatchPrice(pr3, pr1));
    }

    @Test
    public void equalTest() throws IllegalValueException {
        PriceRange pr1 = new PriceRange(45, 100);
        PriceRange pr2 = new PriceRange(45, 100);
        PriceRange pr3 = new PriceRange(45, 99);

        assertTrue(pr1.equals(pr2));
        assertTrue(pr2.equals(pr1));

        assertFalse(pr1.equals(pr3));
    }
}
