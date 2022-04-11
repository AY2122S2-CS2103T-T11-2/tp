package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPARE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortBuyerCommand;


public class SortBuyerCommandParserTest {

    private SortBuyerCommandParser parser = new SortBuyerCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SortBuyerCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_allFieldsPresent_success() {
        System.out.println(PREFIX_COMPARE + "name " + PREFIX_ORDER + "asc");
        assertParseSuccess(parser, " " + PREFIX_COMPARE + "name " + PREFIX_ORDER + "asc",
                new SortBuyerCommand("name", "asc"));
        assertParseSuccess(parser, " " + PREFIX_COMPARE + "name " + PREFIX_ORDER + "desc",
                new SortBuyerCommand("name", "desc"));
        assertParseSuccess(parser, " " + PREFIX_COMPARE + "time " + PREFIX_ORDER + "asc",
                new SortBuyerCommand("time", "asc"));
        assertParseSuccess(parser, " " + PREFIX_COMPARE + "time " + PREFIX_ORDER + "desc",
                new SortBuyerCommand("time", "desc"));
    }


}
