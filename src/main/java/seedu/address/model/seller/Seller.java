package seedu.address.model.seller;

import java.util.Set;

import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a client who is selling houses.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Seller extends Client {
    /**
     * Constructor of Seller class.
     */
    public Seller(Name name, Phone phone, Appointment appointment, Set<Tag> tags) {
        super(name, phone, appointment, tags);
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
