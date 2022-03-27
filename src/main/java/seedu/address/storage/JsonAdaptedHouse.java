package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;

public class JsonAdaptedHouse {

    private final String houseType;
    private final String location;

    /**
     * Constructs a {@code JsonAdaptedHouse} with the given house details.
     */
    @JsonCreator
    public JsonAdaptedHouse(@JsonProperty("houseType") String houseType,
                            @JsonProperty("location") String location) {
        this.houseType = houseType;
        this.location = location;
    }

    /**
     * Converts a given {@code House} into this class for Jackson use.
     */
    public JsonAdaptedHouse(House source) {
        this.houseType = source.getHouseTypeToString();
        this.location = source.getLocationToString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code House} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted house.
     */
    public House toModelType() throws IllegalValueException {
        if (houseType.equals("")) {
            return new House(HouseType.NULLHOUSETYPE, "");
        }
        return new House(HouseType.valueOf(houseType.toUpperCase()), location);
    }
}
