package seedu.address.model.buyer;

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
    private final PropertyToBuy desiredProperty;

    /**
     * Constructor of Buyer class.
     */
    public Buyer(Name name, Phone phone, Appointment appointment, Set<Tag> tags,
                 PropertyToBuy desiredProperty) {
        super(name, phone, appointment, tags);
        this.desiredProperty = desiredProperty;
    }


    /**
     * Returns the property the client wants to buy
     *
     * @return PropertyToBuy.
     */
    public PropertyToBuy getDesiredProperty() {
        return desiredProperty;
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
                && otherBuyer.getDesiredProperty().equals(getDesiredProperty());
    }

}
