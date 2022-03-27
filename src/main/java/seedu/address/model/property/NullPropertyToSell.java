package seedu.address.model.property;

/**
 * Represents a PropertyToBuy instance which has not been assigned any values yet.
 * Exists when the user first creates a Buyer, or if he decides to remove the property later on.
 */
public class NullPropertyToSell extends PropertyToSell {

    private static final NullPropertyToSell nullPropertyToSell =
            new NullPropertyToSell(new House(HouseType.NULLHOUSETYPE, ""),
                    new PriceRange(0, 0), "");

    public NullPropertyToSell(House house, PriceRange priceRange, String address) {
        super(house, priceRange, address);
    }

    public static NullPropertyToSell getNullPropertyToSell() {
        return nullPropertyToSell;
    }

}
