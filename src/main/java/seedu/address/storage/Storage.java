package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBuyerAddressBook;
import seedu.address.model.ReadOnlySellerAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SellerAddressBookStorage,
        BuyerAddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getSellerAddressBookFilePath();

    @Override
    Optional<ReadOnlySellerAddressBook> readSellerAddressBook() throws DataConversionException, IOException;

    @Override
    void saveSellerAddressBook(ReadOnlySellerAddressBook addressBook) throws IOException;

    @Override
    Path getBuyerAddressBookFilePath();

    @Override
    Optional<ReadOnlyBuyerAddressBook> readBuyerAddressBook() throws DataConversionException, IOException;

    @Override
    void saveBuyerAddressBook(ReadOnlyBuyerAddressBook addressBook) throws IOException;
}
