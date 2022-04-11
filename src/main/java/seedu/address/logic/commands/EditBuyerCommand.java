package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BUYERS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;
import seedu.address.model.property.NullPropertyToBuy;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditBuyerCommand extends Command {

    public static final String COMMAND_WORD = "edit-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the buyer identified "
            + "by the index number used in the displayed buyer list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_TAG + "TAG] "
            + "[" + PREFIX_HOUSE_TYPE + "HOUSETYPE] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_PRICE_RANGE + "PRICERANGE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 \n"
            + "Note: You can only edit the property fields ("
            + PREFIX_HOUSE_TYPE + PREFIX_LOCATION + PREFIX_PRICE_RANGE + ") "
            + "after a property has been added!";

    public static final String MESSAGE_EDIT_BUYER_SUCCESS = "Edited buyer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BUYER = "This buyer already exists in the address book.";
    public static final String MESSAGE_NO_PROPERTY_YET = "You cannot edit a property until you have added one!\n"
        + "Use \"add-ptb\" command to add a property first.";


    private final Index index;
    private final EditBuyerDescriptor editBuyerDescriptor;

    /**
     * @param index of the client in the filtered client list to edit
     * @param editBuyerDescriptor details to edit the client with
     */
    public EditBuyerCommand(Index index, EditBuyerDescriptor editBuyerDescriptor) {
        requireNonNull(index);
        requireNonNull(editBuyerDescriptor);

        this.index = index;
        this.editBuyerDescriptor = new EditBuyerDescriptor(editBuyerDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
        }

        Buyer buyerToEdit = lastShownList.get(index.getZeroBased());
        Buyer editedBuyer = createEditedBuyer(buyerToEdit, editBuyerDescriptor);

        if (!buyerToEdit.isSameclient(editedBuyer) && model.hasBuyer(editedBuyer)) {
            throw new CommandException(MESSAGE_DUPLICATE_BUYER);
        }

        model.setBuyer(buyerToEdit, editedBuyer);
        model.updateFilteredBuyerList(PREDICATE_SHOW_ALL_BUYERS);
        return new CommandResult(String.format(MESSAGE_EDIT_BUYER_SUCCESS, editedBuyer));
    }

    /**
     * Creates and returns a {@code client} with the details of {@code buyerToEdit}
     * edited with {@code editBuyerDescriptor}.
     */
    private static Buyer createEditedBuyer(Buyer buyerToEdit, EditBuyerDescriptor editBuyerDescriptor)
            throws CommandException {

        assert buyerToEdit != null;

        if (editBuyerDescriptor.isAnyPropertyFieldEdited()
            && buyerToEdit.getPropertyToBuy() instanceof NullPropertyToBuy) {
            throw new CommandException(MESSAGE_NO_PROPERTY_YET);
        }

        //The Buyer already has a property just update it with new values
        HouseType updatedHouseType = editBuyerDescriptor.getHouseType().orElse(
            buyerToEdit.getPropertyToBuy().getHouse().getHouseType());
        Location updatedLocation = editBuyerDescriptor.getLocation().orElse(
            buyerToEdit.getPropertyToBuy().getHouse().getLocation());
        PriceRange updatedPriceRange = editBuyerDescriptor.getPriceRange().orElse(
            buyerToEdit.getPropertyToBuy().getPriceRange());
        PropertyToBuy updatedPropertyToBuy = buyerToEdit.getPropertyToBuy().updatePropertyToBuy(
            updatedHouseType,
            updatedLocation,
            updatedPriceRange
        );

        Name updatedName = editBuyerDescriptor.getName().orElse(buyerToEdit.getName());
        Phone updatedPhone = editBuyerDescriptor.getPhone().orElse(buyerToEdit.getPhone());
        Set<Tag> updatedTags = editBuyerDescriptor.getTags().orElse(buyerToEdit.getTags());
        Appointment updatedAppointment = editBuyerDescriptor.getAppointment().orElse(buyerToEdit.getAppointment());

        return new Buyer(updatedName, updatedPhone, updatedAppointment, updatedTags, updatedPropertyToBuy);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBuyerCommand)) {
            return false;
        }

        // state check
        EditBuyerCommand e = (EditBuyerCommand) other;
        return index.equals(e.index)
                && editBuyerDescriptor.equals(e.editBuyerDescriptor);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditBuyerDescriptor {
        private Name name;
        private Phone phone;
        private Set<Tag> tags;
        private Appointment appointment;
        private PropertyToBuy propertyToBuy;
        private HouseType houseType;
        private Location location;
        private PriceRange priceRange;

        public EditBuyerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBuyerDescriptor(EditBuyerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setTags(toCopy.tags);
            setAppointment(toCopy.appointment);
            setHouseType(toCopy.houseType);
            setLocation(toCopy.location);
            setPriceRange(toCopy.priceRange);
            setPropertyToBuy(toCopy.propertyToBuy);
        }


        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, tags, houseType, location, priceRange);
        }

        /**
         * Returns true if at least one Property field is edited.
         */
        public boolean isAnyPropertyFieldEdited() {
            return CollectionUtil.isAnyNonNull(houseType, location, priceRange);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setAppointment(Appointment appointment) {
            this.appointment = appointment;
        }

        public Optional<Appointment> getAppointment() {
            return Optional.ofNullable(appointment);
        }

        public void setHouseType(HouseType houseType) {
            this.houseType = houseType;
        }

        public Optional<HouseType> getHouseType() {
            return Optional.ofNullable(houseType);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setPriceRange(PriceRange priceRange) {
            this.priceRange = priceRange;
        }

        public Optional<PriceRange> getPriceRange() {
            return Optional.ofNullable(priceRange);
        }

        public void setPropertyToBuy(PropertyToBuy propertyToBuy) {
            this.propertyToBuy = propertyToBuy;
        }

        public Optional<PropertyToBuy> getPropertyToBuy() {
            return Optional.ofNullable(propertyToBuy);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBuyerDescriptor)) {
                return false;
            }

            // state check
            EditBuyerDescriptor e = (EditBuyerDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getTags().equals(e.getTags())
                    && getAppointment().equals(e.getAppointment())
                    && getHouseType().equals(e.getHouseType())
                    && getLocation().equals(e.getLocation())
                    && getPriceRange().equals(e.getPriceRange());
        }
    }
}
