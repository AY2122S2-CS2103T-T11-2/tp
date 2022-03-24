package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.testutil.ClientBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getclientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateclients_throwsDuplicateclientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newClients);

        assertThrows(DuplicateClientException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasclient_nullclient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasclient(null));
    }

    @Test
    public void hasclient_clientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasclient(ALICE));
    }

    @Test
    public void hasclient_clientInAddressBook_returnsTrue() {
        addressBook.addclient(ALICE);
        assertTrue(addressBook.hasclient(ALICE));
    }

    @Test
    public void hasclient_clientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addclient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasclient(editedAlice));
    }

    @Test
    public void getclientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getclientList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose clients list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();

        AddressBookStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getclientList() {
            return clients;
        }
    }

}
