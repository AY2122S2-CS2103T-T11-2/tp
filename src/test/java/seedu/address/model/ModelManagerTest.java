package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
// import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BUYERS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SELLERS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.BOB;
import static seedu.address.testutil.TypicalSellers.BENSON;
import static seedu.address.testutil.TypicalSellers.CARL;

//import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
//import seedu.address.model.client.NameContainsKeywordsPredicate;
//import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.BuyerAddressBookBuilder;
import seedu.address.testutil.SellerAddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        // assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void equals() {
        BuyerAddressBook buyerAddressBook = new BuyerAddressBookBuilder().withBuyer(ALICE).withBuyer(BOB).build();
        SellerAddressBook sellerAddressBook = new SellerAddressBookBuilder()
            .withSeller(BENSON).withSeller(CARL).build();

        BuyerAddressBook differentBuyerAddressBook = new BuyerAddressBook();
        SellerAddressBook differentSellerAddressBook = new SellerAddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(userPrefs, sellerAddressBook, buyerAddressBook);
        ModelManager modelManagerCopy = new ModelManager(userPrefs, sellerAddressBook, buyerAddressBook);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different buyerAddressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(userPrefs, sellerAddressBook,
                differentBuyerAddressBook)));

        // different sellerAddressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(userPrefs, differentSellerAddressBook,
               buyerAddressBook)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredBuyerList(PREDICATE_SHOW_ALL_BUYERS);
        modelManager.updateFilteredSellerList(PREDICATE_SHOW_ALL_SELLERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(differentUserPrefs, new SellerAddressBook(),
                new BuyerAddressBook())));
    }
}
