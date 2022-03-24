package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditclientDescriptor;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditclientDescriptor objects.
 */
public class EditClientDescriptorBuilder {

    private EditclientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditclientDescriptor();
    }

    public EditClientDescriptorBuilder(EditclientDescriptor descriptor) {
        this.descriptor = new EditclientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditclientDescriptor} with fields containing {@code client}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditclientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setTags(client.getTags());
        descriptor.setAppointment(client.getAppointment());
    }

    /**
     * Sets the {@code Name} of the {@code EditclientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code EditclientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code EditclientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withAppointment(String appointment) {
        descriptor.setAppointment(new Appointment(appointment));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditclientDescriptor}
     * that we are building.
     */
    public EditClientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditclientDescriptor build() {
        return descriptor;
    }
}
