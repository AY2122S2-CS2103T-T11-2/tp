package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBuyerAddressBook;
import seedu.address.model.ReadOnlySellerAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UserPrefsStorage userPrefsStorage;
    private SellerAddressBookStorage sellerAddressBookStorage;
    private BuyerAddressBookStorage buyerAddressBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(UserPrefsStorage userPrefsStorage, SellerAddressBookStorage sellerAddressBookStorage,
                          BuyerAddressBookStorage buyerAddressBookStorage) {
        this.userPrefsStorage = userPrefsStorage;
        this.sellerAddressBookStorage = sellerAddressBookStorage;
        this.buyerAddressBookStorage = buyerAddressBookStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ SellerAddressBook methods ==============================

    @Override
    public Optional<ReadOnlySellerAddressBook> readSellerAddressBook() throws DataConversionException, IOException {
        return readSellerAddressBook(sellerAddressBookStorage.getSellerAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlySellerAddressBook> readSellerAddressBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read seller data from file: " + filePath);
        return sellerAddressBookStorage.readSellerAddressBook(filePath);
    }

    @Override
    public Path getSellerAddressBookFilePath() {
        return sellerAddressBookStorage.getSellerAddressBookFilePath();
    }

    @Override
    public void saveSellerAddressBook(ReadOnlySellerAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to seller data file: " + filePath);
        sellerAddressBookStorage.saveSellerAddressBook(addressBook, filePath);
    }

    @Override
    public void saveSellerAddressBook(ReadOnlySellerAddressBook addressBook) throws IOException {
        saveSellerAddressBook(addressBook, sellerAddressBookStorage.getSellerAddressBookFilePath());
    }

    // ================ BuyerAddressBook methods ==============================

    @Override
    public Optional<ReadOnlyBuyerAddressBook> readBuyerAddressBook() throws DataConversionException, IOException {
        return readBuyerAddressBook(buyerAddressBookStorage.getBuyerAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBuyerAddressBook> readBuyerAddressBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read buyer data from file: " + filePath);
        return buyerAddressBookStorage.readBuyerAddressBook(filePath);
    }

    @Override
    public Path getBuyerAddressBookFilePath() {
        return buyerAddressBookStorage.getBuyerAddressBookFilePath();
    }

    @Override
    public void saveBuyerAddressBook(ReadOnlyBuyerAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to buyer data file: " + filePath);
        buyerAddressBookStorage.saveBuyerAddressBook(addressBook, filePath);
    }

    @Override
    public void saveBuyerAddressBook(ReadOnlyBuyerAddressBook addressBook) throws IOException {
        saveBuyerAddressBook(addressBook, buyerAddressBookStorage.getBuyerAddressBookFilePath());
    }

}
