package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;

/**
 * Jackson-friendly version of {@link PropertyToBuy}.
 */
class JsonAdaptedPropertyToBuy {

    private final JsonAdaptedHouse house;
    private final JsonAdaptedPriceRange priceRange;

    /**
     * Constructs a {@code JsonAdaptedPropertyToBuy} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedPropertyToBuy(@JsonProperty("house") JsonAdaptedHouse house,
                                    @JsonProperty("pricerange") JsonAdaptedPriceRange priceRange) {
        this.house = house;
        this.priceRange = priceRange;
    }

    /**
     * Converts a given {@code PropertyToBuy} into this class for Jackson use.
     */
    public JsonAdaptedPropertyToBuy(PropertyToBuy source) {
        this.house = new JsonAdaptedHouse(source.getHouse());
        this.priceRange = new JsonAdaptedPriceRange(source.getPriceRange());
    }

    public House getHouse() throws IllegalValueException {
        return house.toModelType();
    }

    public PriceRange getPriceRange() throws IllegalValueException {
        return priceRange.toModelType();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PropertyToBuy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PropertyToBuy toModelType() throws IllegalValueException {
        return new PropertyToBuy(house.toModelType(), priceRange.toModelType());
    }


}
