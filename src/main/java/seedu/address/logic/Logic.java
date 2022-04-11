package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBuyerAddressBook;
import seedu.address.model.ReadOnlySellerAddressBook;
import seedu.address.model.buyer.Buyer;
//import seedu.address.model.client.Client;
import seedu.address.model.seller.Seller;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the SellerAddressBook.
     *
     * @see seedu.address.model.Model#getSellerAddressBook()
     */
    ReadOnlySellerAddressBook getSellerAddressBook();

    /** Returns an unmodifiable view of the filtered list of sellers */
    ObservableList<Seller> getFilteredSellerList();

    /**
     * Returns the user prefs' seller address book file path.
     */
    Path getSellerAddressBookFilePath();

    /**
     * Returns the BuyerAddressBook.
     *
     * @see seedu.address.model.Model#getBuyerAddressBook()
     */
    ReadOnlyBuyerAddressBook getBuyerAddressBook();

    /** Returns an unmodifiable view of the filtered list of buyers */
    ObservableList<Buyer> getFilteredBuyerList();

    /**
     * Returns the user prefs' buyers address book file path.
     */
    Path getBuyerAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
