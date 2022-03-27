package seedu.address.model.seller;

import java.util.Set;

import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.PropertyToSell;
import seedu.address.model.tag.Tag;

/**
 * Represents a client who is selling houses.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Seller extends Client {

    //Data fields
    private final PropertyToSell propertyToSell;

    /**
     * Constructor of Seller class.
     */
    public Seller(Name name, Phone phone, Appointment appointment, Set<Tag> tags, PropertyToSell propertyToSell) {
        super(name, phone, appointment, tags);
        this.propertyToSell = propertyToSell;
    }


    /**
     * Returns the property the seller wants to sell
     *
     * @return PropertyToSell.
     */
    public PropertyToSell getPropertyToSell() {
        return propertyToSell;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Seller)) {
            return false;
        }

        Seller otherSeller = (Seller) other;
        return otherSeller.getName().equals(getName())
                && otherSeller.getPhone().equals(getPhone())
                && otherSeller.getTags().equals(getTags());
    }
}
