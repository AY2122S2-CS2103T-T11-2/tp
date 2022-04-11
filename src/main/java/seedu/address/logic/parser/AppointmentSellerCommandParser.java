package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AppointmentSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Appointment;

public class AppointmentSellerCommandParser implements Parser<AppointmentSellerCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AppointmentCommand}
     * and returns a {@code AppointmentCommand} object for execution.
     */
    public AppointmentSellerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentSellerCommand.MESSAGE_USAGE), ive);
        }

        if (!argMultimap.getValue(PREFIX_APPOINTMENT).isPresent()) {
            throw new ParseException(AppointmentSellerCommand.MESSAGE_EMPTY_INPUT_DATE);
        }

        Appointment appointment = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_APPOINTMENT).get());
        if (appointment.appointmentTime == null) {
            return new AppointmentSellerCommand(index, appointment);
        }
        if (!appointment.isLaterThanCurrentTime()) {
            throw new ParseException(AppointmentSellerCommand.MESSAGE_TIME_IN_PAST);
        }
        return new AppointmentSellerCommand(index, appointment);
    }
}
