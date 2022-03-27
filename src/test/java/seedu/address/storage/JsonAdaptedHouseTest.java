package seedu.address.storage;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;

class JsonAdaptedHouseTest {

    private final House house1 = new House(HouseType.NULLHOUSETYPE, "");

    private final JsonAdaptedHouse jsonAdaptedHouse = new JsonAdaptedHouse(house1);


    @Test
    void toModelType() throws IllegalValueException {
    }
}
