package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_APPOINTMENT = "2022-05-01-12-00";

    private Name name;
    private Phone phone;
    private Set<Tag> tags;
    private Appointment appointment;

    /**
     * Creates a {@code clientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        appointment = new Appointment(DEFAULT_APPOINTMENT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the clientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        appointment = clientToCopy.getAppointment();
        tags = new HashSet<>(clientToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code client} that we are building.
     */
    public ClientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code client} that we are building.
     */
    public ClientBuilder withAppointment(String appointment) {
        this.appointment = new Appointment(appointment);
        return this;
    }

    public Client build() {
        return new Client(name, phone, appointment, tags);
    }

}
