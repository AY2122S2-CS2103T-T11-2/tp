package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;

public class AddBuyerCommand extends Command {

    public static final String COMMAND_WORD = "addbuyer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a new buyer. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_REMARK + "REMARK "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Must include: n/ d/ p/ e/ e/ a/ r/, 't/' is optional\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DESCRIPTION + "A boy "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_REMARK + "aggressive"
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New buyer added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This buyer already exists";

    private final Buyer toAdd;

    /**
     * Creates an AddBuyerCommand to add the specified {@code buyer}
     */
    public AddBuyerCommand(Buyer buyer) {
        requireNonNull(buyer);
        toAdd = buyer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBuyer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addBuyer(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBuyerCommand // instanceof handles nulls
                && toAdd.equals(((AddBuyerCommand) other).toAdd));
    }
}
