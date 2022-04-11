package seedu.address.model.property;


/**
 * A demand indicating the property a buyer wants to buy a house for, and the PriceRange it is willing to buy for.
 * Note that a buyer is not looking for a specific address, unlike the seller who knows the specific address of the
 * house it is selling.
 */
public class PropertyToBuy {

    /**
     * The House a buyer is interested in buying.
     */
    private House house;

    /**
     * The PriceRange a buyer is willing to pay to buy the House.
     */
    private PriceRange priceRange;

    /**
     * Constructs a PropertyToBuy of the buyer.
     * @param house the house that a buyer wants to buy.
     * @param priceRange the PriceRange that a buyer is willing to pay.
     */
    public PropertyToBuy(House house, PriceRange priceRange) {
        this.house = house;
        this.priceRange = priceRange;
    }

    /**
     * Update the PropertyToBuy information by calling this method, this method will be used
     * in {@code EditBuyerCommand} class.
     * @param houseType
     * @param location
     * @param priceRange
     * @return a new updated PropertyToBuy
     */
    public PropertyToBuy updatePropertyToBuy(HouseType houseType, Location location,
                                               PriceRange priceRange) {
        this.house = new House(houseType, location);
        this.priceRange = priceRange;
        return this;
    }

    public House getHouse() {
        return this.house;
    }

    public PriceRange getPriceRange() {
        return this.priceRange;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PropertyToBuy // instanceof handles nulls
            && house.equals(((PropertyToBuy) other).house)
            && priceRange.equals(((PropertyToBuy) other).priceRange)); // state check
    }

    @Override
    public String toString() {
        return getHouse().toString() + "\n" + "Price Range: " + getPriceRange().toString();
    }
}
