package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.tag.Tag;

public class AddPropertyToBuyCommand extends Command {

    public static final String COMMAND_WORD = "add-ptb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a new buyer's property. "
            + "Parameters: "
            + "INDEX "
            + PREFIX_HOUSE_TYPE + "HOUSE TYPE "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_PRICE_RANGE + "PRICE RANGE "
            + "Must include: index h/ l/ pr/ \n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_HOUSE_TYPE + "HDB "
            + PREFIX_LOCATION + "Bishan "
            + PREFIX_PRICE_RANGE + "400000,500000 ";

    public static final String MESSAGE_SUCCESS = "New property added for buyer: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "The buyer already has this property";

    private final Index index;
    private final PropertyToBuy propertyToBuy;

    /**
     * Creates an AddBuyerCommand to add the specified {@code buyer}
     */
    public AddPropertyToBuyCommand(Index index, PropertyToBuy propertyToBuy) {
        requireNonNull(propertyToBuy);
        requireNonNull(index);

        this.index = index;
        this.propertyToBuy = propertyToBuy;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Buyer buyerToUpdate = lastShownList.get(index.getZeroBased());

        if (buyerToUpdate.getPropertyToBuy().equals(propertyToBuy)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }
        Buyer buyerWithProperty = createPropertyForBuyer(buyerToUpdate, propertyToBuy);
        model.setBuyer(buyerToUpdate, buyerWithProperty);
        model.updateFilteredBuyerList(Model.PREDICATE_SHOW_ALL_BUYERS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, buyerWithProperty));
    }

    /**
     * Creates a Buyer instance with the updated PropertyToBuy.
     *
     * @param buyer The original buyer.
     * @param property The new PropertyToBuy.
     * @return A new Buyer.
     */
    public Buyer createPropertyForBuyer(Buyer buyer, PropertyToBuy property) {
        assert buyer != null;

        Name buyerName = buyer.getName();
        Phone buyerPhone = buyer.getPhone();
        Set<Tag> buyerTags = buyer.getTags();
        Appointment buyerAppointment = buyer.getAppointment();

        return new Buyer(buyerName, buyerPhone, buyerAppointment, buyerTags, property);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPropertyToBuyCommand // instanceof handles nulls
                && propertyToBuy.equals(((AddPropertyToBuyCommand) other).propertyToBuy));
    }
}
