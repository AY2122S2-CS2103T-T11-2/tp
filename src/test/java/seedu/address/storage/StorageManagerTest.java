package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyerAddressBook;
import static seedu.address.testutil.TypicalSellers.getTypicalSellerAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.BuyerAddressBook;
import seedu.address.model.ReadOnlyBuyerAddressBook;
import seedu.address.model.ReadOnlySellerAddressBook;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonSellerAddressBookStorage sellerAddressBookStorage = new JsonSellerAddressBookStorage(
                getTempFilePath("sab")
        );
        JsonBuyerAddressBookStorage buyerAddressBookStorage = new JsonBuyerAddressBookStorage(
                getTempFilePath("bab"));
        storageManager = new StorageManager(userPrefsStorage, sellerAddressBookStorage,
                buyerAddressBookStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }


    @Test
    public void buyerAddressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        BuyerAddressBook original = getTypicalBuyerAddressBook();
        storageManager.saveBuyerAddressBook(original);
        ReadOnlyBuyerAddressBook retrieved = storageManager.readBuyerAddressBook().get();
        assertEquals(original, new BuyerAddressBook(retrieved));
    }

    @Test
    public void sellerAddressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        SellerAddressBook original = getTypicalSellerAddressBook();
        storageManager.saveSellerAddressBook(original);
        ReadOnlySellerAddressBook retrieved = storageManager.readSellerAddressBook().get();
        assertEquals(original, new SellerAddressBook(retrieved));
    }

    @Test
    public void getBuyerAddressBookFilePath() {
        assertNotNull(storageManager.getBuyerAddressBookFilePath());
    }

    @Test
    public void getSellerAddressBookFilePath() {
        assertNotNull(storageManager.getSellerAddressBookFilePath());
    }

}
