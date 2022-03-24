package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlySellerAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonSellerAddressBookStorage implements SellerAddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSellerAddressBookStorage.class);

    private Path filePath;

    public JsonSellerAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSellerAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySellerAddressBook> readSellerAddressBook() throws DataConversionException {
        return readSellerAddressBook(filePath);
    }

    /**
     * Similar to {@link #readSellerAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySellerAddressBook> readSellerAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSellerAddressBook> jsonSellerAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableSellerAddressBook.class);
        if (!jsonSellerAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSellerAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSellerAddressBook(ReadOnlySellerAddressBook addressBook) throws IOException {
        saveSellerAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveSellerAddressBook(ReadOnlySellerAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook, Path filePath) throws IOException {
        requireNonNull(sellerAddressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSellerAddressBook(sellerAddressBook), filePath);
    }

}
