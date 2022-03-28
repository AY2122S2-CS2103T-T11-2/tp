package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddPropertyToBuyCommand;
import seedu.address.logic.commands.AddPropertyToSellCommand;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.commands.AppointmentBuyerCommand;
import seedu.address.logic.commands.AppointmentSellerCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteBuyerCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MatchCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddSellerCommand.COMMAND_WORD:
            return new AddSellerCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        //case EditSellerCommand.COMMAND_WORD:
        //    return new EditSellerCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        
        case DeleteBuyerCommand.COMMAND_WORD:
            return new DeleteBuyerCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommand();

        case AppointmentBuyerCommand.COMMAND_WORD:
            return new AppointmentBuyerCommandParser().parse(arguments);

        case AppointmentSellerCommand.COMMAND_WORD:
            return new AppointmentSellerCommandParser().parse(arguments);

        case AddBuyerCommand.COMMAND_WORD:
            return new AddBuyerCommandParser().parse(arguments);

        case AddPropertyToBuyCommand.COMMAND_WORD:
            return new AddPropertyToBuyCommandParser().parse(arguments);

        case AddPropertyToSellCommand.COMMAND_WORD:
            return new AddPropertyToSellCommandParser().parse(arguments);

        case MatchCommand.COMMAND_WORD:
            return new MatchCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
