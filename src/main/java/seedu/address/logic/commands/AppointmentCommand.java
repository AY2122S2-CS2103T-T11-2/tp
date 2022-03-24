package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;



public class AppointmentCommand extends Command {
    public static final String COMMAND_WORD = "appointment";
    public static final String MESSAGE_EMPTY_INPUT_DATE = "Please enter the date following the client index"
            + " in the form of 'yyyy-MM-dd-HH-mm'";
    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "You have made an appointment with client: %1$s";
    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "You have removed an appointment with client: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": The appointment time should be specified to minutes"
            + "with the format 'yyyy-MM-dd-HH-mm'\n"
            + "Example:  " + COMMAND_WORD + " 1 "
            + "time/ 2022-05-04-14-00";

    private final Index index;
    private final Appointment appointment;

    /**
     * Creates an AppointmentCommand to make an appointment with
     * a specified {@code client}
     * @param index The person to arrange an appointment with.
     * @param appointment The detailed time of the appointment.
     */
    public AppointmentCommand(Index index, Appointment appointment) {
        requireAllNonNull(index, appointment);
        this.index = index;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = new Client(
                clientToEdit.getName(), clientToEdit.getPhone(), appointment, clientToEdit.getTags()
        );

        model.setClient(clientToEdit, editedClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        return new CommandResult(generateSuccessMessage(editedClient));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentCommand)) {
            return false;
        }

        AppointmentCommand a = (AppointmentCommand) other;
        return index.equals(a.index)
                && appointment.equals(a.appointment);
    }

    private String generateSuccessMessage(Client clientToEdit) {
        String message = !appointment.value.isEmpty()
                ? MESSAGE_ADD_APPOINTMENT_SUCCESS : MESSAGE_DELETE_APPOINTMENT_SUCCESS;
        return String.format(message, clientToEdit);
    }

}
