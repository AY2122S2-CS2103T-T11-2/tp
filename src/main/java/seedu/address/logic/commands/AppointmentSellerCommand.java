package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BUYERS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Appointment;
import seedu.address.model.seller.Seller;

public class AppointmentSellerCommand extends Command {
    public static final String COMMAND_WORD = "appt-s";
    public static final String MESSAGE_EMPTY_INPUT_DATE = "Please enter the date following the seller index\n"
            + " in the form of 'yyyy-MM-dd-HH-mm'";
    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "You have made an appointment with seller: %1$s";
    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "You have removed an appointment with seller: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Makes an appointment with a seller\n"
            + "Parameters: INDEX " + PREFIX_APPOINTMENT
            + "TIME\n"
            + "Must include: INDEX time/ \n"
            + "Ensure INDEX is positive integer & less than or equal to size of displayed seller list \n"
            + "The appointment time should be specified to minutes "
            + "with the format 'yyyy-MM-dd-HH-mm.'\n"
            + "Example:  " + COMMAND_WORD + " 1 "
            + "time/ 2022-05-04-14-00";
    public static final String MESSAGE_TIME_IN_PAST = "The time you entered is in the past\n"
            + "Please enter a time in the future";

    private final Index index;
    private final Appointment appointment;

    /**
     * Creates an AppointmentCommand to make an appointment with
     * a specified {@code client}
     * @param index The person to arrange an appointment with.
     * @param appointment The detailed time of the appointment.
     */
    public AppointmentSellerCommand(Index index, Appointment appointment) {
        requireAllNonNull(index, appointment);
        this.index = index;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Seller> lastShownList = model.getFilteredSellerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
        }

        Seller sellerToEdit = lastShownList.get(index.getZeroBased());
        Seller editedSeller = new Seller(
                sellerToEdit.getName(), sellerToEdit.getPhone(), appointment, sellerToEdit.getTags(),
                sellerToEdit.getPropertyToSell()
        );

        model.setSeller(sellerToEdit, editedSeller);
        model.updateFilteredBuyerList(PREDICATE_SHOW_ALL_BUYERS);

        return new CommandResult(generateSuccessMessage(editedSeller));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentSellerCommand)) {
            return false;
        }

        AppointmentSellerCommand a = (AppointmentSellerCommand) other;
        return index.equals(a.index)
                && appointment.equals(a.appointment);
    }

    private String generateSuccessMessage(Seller sellerToEdit) {
        String message = !appointment.value.isEmpty()
                ? MESSAGE_ADD_APPOINTMENT_SUCCESS : MESSAGE_DELETE_APPOINTMENT_SUCCESS;
        return String.format(message, sellerToEdit);
    }
}

