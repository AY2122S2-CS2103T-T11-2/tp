package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.PriceRange;

class JsonAdaptedPriceRangeTest {

    private final PriceRange priceRange1 = new PriceRange(1, 100);
    private final PriceRange priceRange2 = new PriceRange(50, 100);

    private final JsonAdaptedPriceRange jsonAdaptedPriceRange1 = new JsonAdaptedPriceRange("1", "100");
    private final JsonAdaptedPriceRange jsonAdaptedPriceRange2 = new JsonAdaptedPriceRange("50", "100");


    @Test
    void toModelType() throws IllegalValueException {
        assertEquals(priceRange1, jsonAdaptedPriceRange1.toModelType());
        assertEquals(priceRange2, jsonAdaptedPriceRange2.toModelType());
    }
}
