package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class PropertyToSellTest {

    private House houseStub1 = new House(HouseType.APARTMENT, "Serangoon");
    private PriceRange priceRangeStub1 = new PriceRange(0, 100);
    private PropertyToSell toSell1 = new PropertyToSell(houseStub1, priceRangeStub1, "postal 533333");

    private House houseStub2 = new House(HouseType.APARTMENT, "Serangoon");
    private PriceRange priceRangeStub2 = new PriceRange(0, 100);
    private PropertyToSell toSell2 = new PropertyToSell(houseStub2, priceRangeStub2, "postal 533333");


    @Test
    public void equalsTest() {
        assertTrue(toSell1.equals(toSell2));
        assertTrue(toSell2.equals(toSell1));
    }
}
