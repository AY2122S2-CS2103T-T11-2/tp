package seedu.address.model.buyer;

import java.util.List;
import java.util.Set;

import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.tag.Tag;

/**
 * Represents a client who is buying houses.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Buyer extends Client {

    // Data fields
    private final List<PropertyToBuy> propertiesToBuy;

    /**
     * Constructor of Buyer class.
     */
    public Buyer(Name name, Phone phone, Appointment appointment, Set<Tag> tags,
                 List<PropertyToBuy> properties) {
        super(name, phone, appointment, tags);
        this.propertiesToBuy = properties;
    }

    /**
     * Returns the properties the client is willing to buy.
     *
     * @return List of PropertyToBuy.
     */
    public List<PropertyToBuy> getPropertiesToBuy() {
        return propertiesToBuy;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Buyer)) {
            return false;
        }

        Buyer otherBuyer = (Buyer) other;
        return otherBuyer.getName().equals(getName())
                && otherBuyer.getPhone().equals(getPhone())
                && otherBuyer.getTags().equals(getTags())
                && otherBuyer.hasSameProperties(this);
    }

    /**
     * Checks if 2 Buyers have the matching properties.
     *
     * @param other The other Buyer.
     * @return Whether their properties match.
     */
    public boolean hasSameProperties(Buyer other) {
        return propertiesToBuy.containsAll(other.propertiesToBuy)
                && other.propertiesToBuy.containsAll(propertiesToBuy);

    }
}
