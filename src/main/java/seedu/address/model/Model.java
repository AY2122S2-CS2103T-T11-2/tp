package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Client;
import seedu.address.model.seller.Seller;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    Predicate<Seller> PREDICATE_SHOW_ALL_SELLERS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the user prefs' seller address book file path.
     */
    Path getSellerAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setSellerAddressBookFilePath(Path sellerAddressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook);

    /** Returns the AddressBook */
    ReadOnlySellerAddressBook getSellerAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteClient(Client target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addClient(Client client);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setClient(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */

    void updateFilteredClientList(Predicate<Client> predicate);

    void sortFilteredClientList();

    //========== For addbuyer============//

    /**
     * Checks if the same buyer already exists in AgentSee.
     *
     * @param buyer The buyer to be checked.
     * @return Whether buyer exists in AgentSee.
     */
    boolean hasBuyer(Buyer buyer);

    /**
     * Adds a new buyer to AgentSee.
     *
     * @param buyer The buyer to be added.
     */
    void addBuyer(Buyer buyer);

    //========== For addseller============//

    /**
     * Checks if the same buyer already exists in AgentSee.
     *
     * @param seller The buyer to be checked.
     * @return Whether buyer exists in AgentSee.
     */
    boolean hasSeller(Seller seller);

    /**
     * Adds a new buyer to AgentSee.
     *
     * @param seller The buyer to be added.
     */
    void addSeller(Seller seller);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteSeller(Seller target);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setSeller(Seller target, Seller editedSeller);

    /** Returns an unmodifiable view of the filtered seller list */
    ObservableList<Seller> getFilteredSellerList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */

    void updateFilteredSellerList(Predicate<Seller> predicate);

}
