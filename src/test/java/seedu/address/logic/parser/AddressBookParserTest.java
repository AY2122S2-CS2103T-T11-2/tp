package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PROPERTY_BUY_CHAD;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.CHAD;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.AddPropertyToBuyCommand;
import seedu.address.logic.commands.DeleteBuyerCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.Buyer;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.BuyerUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_deleteBuyer() throws Exception {
        DeleteBuyerCommand command = (DeleteBuyerCommand) parser.parseCommand(
                DeleteBuyerCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased());
        assertEquals(new DeleteBuyerCommand(INDEX_FIRST_CLIENT), command);
    }

    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_addbuyer() throws Exception {
        Buyer buyer = new BuyerBuilder().build();
        AddBuyerCommand command = (AddBuyerCommand) parser.parseCommand(BuyerUtil.getAddBuyerCommand(buyer));
        AddBuyerCommand c = new AddBuyerCommand(buyer);
        assertEquals(c, command);
    }

    @Test
    public void parseCommand_addPropertyToBuy() throws Exception {
        Buyer buyer = new BuyerBuilder(CHAD).build();
        AddPropertyToBuyCommand command = (AddPropertyToBuyCommand)
                parser.parseCommand(AddPropertyToBuyCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased() + " " + BuyerUtil.getPropertyToBuyCommand(buyer));
        AddPropertyToBuyCommand c = new AddPropertyToBuyCommand(INDEX_FIRST_CLIENT,
                VALID_PROPERTY_BUY_CHAD);
        boolean a = c.equals(command);
        assertTrue(a);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
