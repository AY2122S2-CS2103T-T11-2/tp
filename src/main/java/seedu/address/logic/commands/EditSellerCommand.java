package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

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
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditSellerCommand extends Command {

    public static final String COMMAND_WORD = "editseller";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the seller identified "
            + "by the index number used in the displayed seller list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 ";

    public static final String MESSAGE_EDIT_SELLER_SUCCESS = "Edited seller: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SELLER = "This seller already exists in the address book.";

    private final Index index;
    private final EditSellerDescriptor editSellerDescriptor;

    /**
     * @param index of the client in the filtered client list to edit
     * @param editSellerDescriptor details to edit the client with
     */
    public EditSellerCommand(Index index, EditSellerDescriptor editSellerDescriptor) {
        requireNonNull(index);
        requireNonNull(editSellerDescriptor);

        this.index = index;
        this.editSellerDescriptor = new EditSellerDescriptor(editSellerDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Seller> lastShownList = model.getFilteredSellerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Seller sellerToEdit = lastShownList.get(index.getZeroBased());
        Seller editedSeller = createEditedSeller(sellerToEdit, editSellerDescriptor);

        if (!sellerToEdit.isSameclient(editedSeller) && model.hasClient(editedSeller)) {
            throw new CommandException(MESSAGE_DUPLICATE_SELLER);
        }

        model.setSeller(sellerToEdit, editedSeller);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_SELLER_SUCCESS, editedSeller));
    }

    /**
     * Creates and returns a {@code client} with the details of {@code sellerToEdit}
     * edited with {@code editSellerDescriptor}.
     */
    private static Seller createEditedSeller(Seller sellerToEdit, EditSellerDescriptor editSellerDescriptor) {
        assert sellerToEdit != null;

        Name updatedName = editSellerDescriptor.getName().orElse(sellerToEdit.getName());
        Phone updatedPhone = editSellerDescriptor.getPhone().orElse(sellerToEdit.getPhone());
        Set<Tag> updatedTags = editSellerDescriptor.getTags().orElse(sellerToEdit.getTags());
        Appointment updatedAppointment = editSellerDescriptor.getAppointment().orElse(sellerToEdit.getAppointment());

        return new Seller(updatedName, updatedPhone, updatedAppointment, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSellerCommand)) {
            return false;
        }

        // state check
        EditSellerCommand e = (EditSellerCommand) other;
        return index.equals(e.index)
                && editSellerDescriptor.equals(e.editSellerDescriptor);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditSellerDescriptor {
        private Name name;
        private Phone phone;
        private Set<Tag> tags;
        private Appointment appointment;

        public EditSellerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSellerDescriptor(EditSellerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setTags(toCopy.tags);
        }


        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, tags);
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
            if (!(other instanceof EditSellerDescriptor)) {
                return false;
            }

            // state check
            EditSellerDescriptor e = (EditSellerDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getTags().equals(e.getTags());
        }
    }
}
