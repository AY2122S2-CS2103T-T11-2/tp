package seedu.address.model.seller;

import java.util.function.Predicate;

import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.property.PropertyToSell;

/**
 * Tests that a {@code Seller}'s {@code PropertyToSell} matches the demand
 *  of a given {@code Buyer}'s {@code PropertyToBuy}.
 */
public class HasMatchWithBuyerPredicate implements Predicate<Seller> {

    /**
     * Index of Buyer in UniqueBuyerList to match with.
     */
    private final Buyer buyer;

    public HasMatchWithBuyerPredicate(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * Tests whether the Buyer (provided by the index), matches with a Seller.
     * @param seller the seller we are matching the Buyer with.
     * @return True if {@code Seller}'s {@code PropertyToSell} matches the demand
     * of the {@code Buyer}'s {@code PropertyToBuy}.
     */
    @Override
    public boolean test(Seller seller) {
        PropertyToBuy propertyToBuy = getPropertyToBuy();
        PropertyToSell propertyToSell = getPropertyToSell(seller);

        PriceRange buyRange = getBuyRange(propertyToBuy);
        PriceRange sellRange = getSellRange(propertyToSell);

        boolean isMatchedPrices = PriceRange.canMatchPrice(buyRange, sellRange);

        boolean isEqualHouse = propertyToBuy.getHouse().equals(propertyToSell.getHouse());

        return isMatchedPrices && isEqualHouse;
    }

    public PropertyToBuy getPropertyToBuy() {
        return buyer.getPropertyToBuy();
    }

    public PropertyToSell getPropertyToSell(Seller seller) {
        return seller.getPropertyToSell();
    }

    public PriceRange getBuyRange(PropertyToBuy property) {
        return property.getPriceRange();
    }

    public PriceRange getSellRange(PropertyToSell property) {
        return property.getPriceRange();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof HasMatchWithBuyerPredicate // instanceof handles nulls
            && buyer.equals(((HasMatchWithBuyerPredicate) other).buyer)); // state check
    }
}
