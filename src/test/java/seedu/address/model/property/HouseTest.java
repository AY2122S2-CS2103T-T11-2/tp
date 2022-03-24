package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HouseTest {

    private House h1 = new House(HouseType.BUNGALOW, "Serangoon");
    private House h2 = new House(HouseType.BUNGALOW, "Kovan");
    private House h3 = new House(HouseType.APARTMENT, "Serangoon");
    private House h4 = new House(HouseType.BUNGALOW, "Serangoon");

    @Test
    public void testHouseEquals() {
        assertFalse(h1.equals(h2));
        assertFalse(h1.equals(h3));
        assertTrue(h1.equals(h4));
    }
}
