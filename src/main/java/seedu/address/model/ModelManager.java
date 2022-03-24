package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Client;
import seedu.address.model.seller.Seller;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final SellerAddressBook sellerAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Seller> filteredSellers;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook,
                        ReadOnlyUserPrefs userPrefs, ReadOnlySellerAddressBook sellerAddressBook) {
        requireAllNonNull(addressBook, userPrefs, sellerAddressBook);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs
                + " and seller address book: " + sellerAddressBook);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.sellerAddressBook = new SellerAddressBook(sellerAddressBook);
        filteredClients = new FilteredList<>(this.addressBook.getclientList());
        filteredSellers = new FilteredList<>(this.sellerAddressBook.getSellerList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new SellerAddressBook());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getSellerAddressBookFilePath() {
        return userPrefs.getSellerAddressBookFilePath();
    }

    @Override
    public void setSellerAddressBookFilePath(Path sellerAddressBookFilePath) {
        requireNonNull(sellerAddressBookFilePath);
        userPrefs.setSellerAddressBookFilePath(sellerAddressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasclient(client);
    }

    @Override
    public void deleteClient(Client target) {
        addressBook.removeclient(target);
    }

    @Override
    public void addClient(Client client) {
        addressBook.addclient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        addressBook.setclient(target, editedClient);
    }

    //========== For addbuyer============//
    @Override
    public void addBuyer(Buyer buyer) {
        addressBook.addBuyer(buyer);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return addressBook.hasBuyer(buyer);
    }

    //========== For addseller============//
    @Override
    public void addSeller(Seller seller) {
        sellerAddressBook.addSeller(seller);
        updateFilteredSellerList(PREDICATE_SHOW_ALL_SELLERS);
    }

    @Override
    public boolean hasSeller(Seller seller) {
        requireNonNull(seller);
        return sellerAddressBook.hasSeller(seller);
    }

    @Override
    public void deleteSeller(Seller target) {
        sellerAddressBook.removeSeller(target);
    }

    @Override
    public void setSeller(Seller target, Seller editedSeller) {
        requireAllNonNull(target, editedSeller);
        sellerAddressBook.setSeller(target, editedSeller);
    }

    @Override
    public ObservableList<Seller> getFilteredSellerList() {
        return filteredSellers;
    }

    @Override
    public void updateFilteredSellerList(Predicate<Seller> predicate) {
        requireNonNull(predicate);
        filteredSellers.setPredicate(predicate);
    }

    @Override
    public void setSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook) {
        this.sellerAddressBook.resetData(sellerAddressBook);
    }

    @Override
    public ReadOnlySellerAddressBook getSellerAddressBook() {
        return sellerAddressBook;
    }

    //=========== Filtered client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code client} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public void sortFilteredClientList() {
        addressBook.sortPersons();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        // add buyer and seller later
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients);
    }

}
