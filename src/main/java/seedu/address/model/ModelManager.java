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
//import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.seller.Seller;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);


    private final SellerAddressBook sellerAddressBook;
    private final BuyerAddressBook buyerAddressBook;
    private final UserPrefs userPrefs;
    // private final FilteredList<Client> filteredClients;
    private final FilteredList<Seller> filteredSellers;
    private final FilteredList<Buyer> filteredBuyers;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyUserPrefs userPrefs,
                        ReadOnlySellerAddressBook sellerAddressBook,
                        ReadOnlyBuyerAddressBook buyerAddressBook) {
        requireAllNonNull(userPrefs, sellerAddressBook);

        logger.fine("Initializing with user prefs " + userPrefs
                + ", buyer address book:" + buyerAddressBook
                + " and seller address book: " + sellerAddressBook);

        this.userPrefs = new UserPrefs(userPrefs);
        this.sellerAddressBook = new SellerAddressBook(sellerAddressBook);
        this.buyerAddressBook = new BuyerAddressBook(buyerAddressBook);
        filteredSellers = new FilteredList<>(this.sellerAddressBook.getSellerList());
        filteredBuyers = new FilteredList<>(this.buyerAddressBook.getBuyerList());
    }

    public ModelManager() {
        this(new UserPrefs(), new SellerAddressBook(), new BuyerAddressBook());
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
    public Path getSellerAddressBookFilePath() {
        return userPrefs.getSellerAddressBookFilePath();
    }

    @Override
    public void setSellerAddressBookFilePath(Path sellerAddressBookFilePath) {
        requireNonNull(sellerAddressBookFilePath);
        userPrefs.setSellerAddressBookFilePath(sellerAddressBookFilePath);
    }

    @Override
    public Path getBuyerAddressBookFilePath() {
        return userPrefs.getBuyerAddressBookFilePath();
    }

    @Override
    public void setBuyerAddressBookFilePath(Path buyerAddressBookFilePath) {
        requireNonNull(buyerAddressBookFilePath);
        userPrefs.setBuyerAddressBookFilePath(buyerAddressBookFilePath);
    }

    //========== For addbuyer============//
    @Override
    public void addBuyer(Buyer buyer) {
        buyerAddressBook.addBuyer(buyer);
        updateFilteredBuyerList(PREDICATE_SHOW_ALL_BUYERS);
    }

    @Override
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return buyerAddressBook.hasBuyer(buyer);
    }

    @Override
    public void deleteBuyer(Buyer target) {
        buyerAddressBook.removeBuyer(target);
    }

    @Override
    public void setBuyer(Buyer target, Buyer editedBuyer) {
        requireAllNonNull(target, editedBuyer);
        buyerAddressBook.setBuyer(target, editedBuyer);
    }

    @Override
    public ObservableList<Buyer> getFilteredBuyerList() {
        return filteredBuyers;
    }

    @Override
    public void updateFilteredBuyerList(Predicate<Buyer> predicate) {
        requireNonNull(predicate);
        filteredBuyers.setPredicate(predicate);
    }

    @Override
    public void setBuyerAddressBook(ReadOnlyBuyerAddressBook buyerAddressBook) {
        this.buyerAddressBook.resetData(buyerAddressBook);
    }

    @Override
    public ReadOnlyBuyerAddressBook getBuyerAddressBook() {
        return buyerAddressBook;
    }

    @Override
    public void sortFilteredBuyerList(String comparator, String order) {
        buyerAddressBook.sortBuyers(comparator, order);
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

    @Override
    public void sortFilteredSellerList(String comparator, String order) {
        sellerAddressBook.sortSellers(comparator, order);
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
        return buyerAddressBook.equals(other.buyerAddressBook)
                && sellerAddressBook.equals(other.sellerAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredBuyers.equals(other.filteredBuyers)
                && filteredSellers.equals(other.filteredSellers);
    }


}
