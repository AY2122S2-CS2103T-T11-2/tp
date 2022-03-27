package seedu.address.model.property;

/**
 * Represents a PropertyToBuy instance which has not been assigned any values yet.
 * Exists when the user first creates a Buyer, or if he decides to remove the property later on.
 */
public class NullPropertyToBuy extends PropertyToBuy {

    private static final NullPropertyToBuy nullPropertyToBuy =
            new NullPropertyToBuy(new House(HouseType.NULLHOUSETYPE, ""),
                    new PriceRange(0, 0));

    public NullPropertyToBuy(House house, PriceRange priceRange) {
        super(house, priceRange);
    }

    public static NullPropertyToBuy getNullPropertyToBuy() {
        return nullPropertyToBuy;
    }

}
