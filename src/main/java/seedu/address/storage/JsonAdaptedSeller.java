package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.NullPropertyToSell;
import seedu.address.model.property.PropertyToSell;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;

public class JsonAdaptedSeller {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "seller's %s field is missing!";

    private final String name;
    private final String phone;
    private final String appointment;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final JsonAdaptedPropertyToSell propertyToSell;

    /**
     * Constructs a {@code JsonAdaptedclient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedSeller(@JsonProperty("name") String name,
                            @JsonProperty("phone") String phone,
                            @JsonProperty("appointment") String appointment,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("propertyToSell") JsonAdaptedPropertyToSell propertyToSell) {
        this.name = name;
        this.phone = phone;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.appointment = appointment;
        this.propertyToSell = propertyToSell;
    }

    /**
     * Converts a given {@code client} into this class for Jackson use.
     */
    public JsonAdaptedSeller(Seller source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        appointment = source.getAppointment().value;
        propertyToSell = new JsonAdaptedPropertyToSell(source.getPropertyToSell());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Buyer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Seller toModelType() throws IllegalValueException {
        final List<Tag> buyerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            buyerTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (appointment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Appointment.class.getSimpleName()));
        }

        final Appointment modelAppointment = new Appointment(appointment);

        final Set<Tag> modelTags = new HashSet<>(buyerTags);

        final PropertyToSell propertyToSell = this.propertyToSell == null
                ? NullPropertyToSell.getNullPropertyToSell() : new PropertyToSell(this.propertyToSell.getHouse(),
                this.propertyToSell.getPriceRange(), this.propertyToSell.getAddress());
        return new Seller(modelName, modelPhone, modelAppointment, modelTags, propertyToSell);
    }
}
