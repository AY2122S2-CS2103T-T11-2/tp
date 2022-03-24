package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.seller.Seller;

/**
 * Adds a client to the address book.
 */
public class AddSellerCommand extends Command {

    public static final String COMMAND_WORD = "addseller";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a seller to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Must include: n/ p/ , 't/' is optional\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New seller added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This seller already exists in the address book";

    private final Seller toAdd;

    /**
     * Creates an AddCommand to add the specified {@code client}
     */
    public AddSellerCommand(Seller seller) {
        requireNonNull(seller);
        toAdd = seller;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSeller(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addSeller(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSellerCommand // instanceof handles nulls
                && toAdd.equals(((AddSellerCommand) other).toAdd));
    }
}
