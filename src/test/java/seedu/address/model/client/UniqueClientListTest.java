package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.testutil.ClientBuilder;

public class UniqueClientListTest {

    private final UniqueClientList uniqueclientList = new UniqueClientList();

    @Test
    public void contains_nullclient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueclientList.contains(null));
    }

    @Test
    public void contains_clientNotInList_returnsFalse() {
        assertFalse(uniqueclientList.contains(ALICE));
    }

    @Test
    public void contains_clientInList_returnsTrue() {
        uniqueclientList.add(ALICE);
        assertTrue(uniqueclientList.contains(ALICE));
    }

    @Test
    public void contains_clientWithSameIdentityFieldsInList_returnsTrue() {
        uniqueclientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueclientList.contains(editedAlice));
    }

    @Test
    public void add_nullclient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueclientList.add(null));
    }

    @Test
    public void add_duplicateclient_throwsDuplicateclientException() {
        uniqueclientList.add(ALICE);
        assertThrows(DuplicateClientException.class, () -> uniqueclientList.add(ALICE));
    }

    @Test
    public void setclient_nullTargetclient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueclientList.setclient(null, ALICE));
    }

    @Test
    public void setclient_nullEditedclient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueclientList.setclient(ALICE, null));
    }

    @Test
    public void setclient_targetclientNotInList_throwsclientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueclientList.setclient(ALICE, ALICE));
    }

    @Test
    public void setclient_editedclientIsSameclient_success() {
        uniqueclientList.add(ALICE);
        uniqueclientList.setclient(ALICE, ALICE);
        UniqueClientList expectedUniqueclientList = new UniqueClientList();
        expectedUniqueclientList.add(ALICE);
        assertEquals(expectedUniqueclientList, uniqueclientList);
    }

    @Test
    public void setclient_editedclientHasSameIdentity_success() {
        uniqueclientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueclientList.setclient(ALICE, editedAlice);
        UniqueClientList expectedUniqueclientList = new UniqueClientList();
        expectedUniqueclientList.add(editedAlice);
        assertEquals(expectedUniqueclientList, uniqueclientList);
    }

    @Test
    public void setclient_editedclientHasDifferentIdentity_success() {
        uniqueclientList.add(ALICE);
        uniqueclientList.setclient(ALICE, BOB);
        UniqueClientList expectedUniqueclientList = new UniqueClientList();
        expectedUniqueclientList.add(BOB);
        assertEquals(expectedUniqueclientList, uniqueclientList);
    }

    @Test
    public void setclient_editedclientHasNonUniqueIdentity_throwsDuplicateclientException() {
        uniqueclientList.add(ALICE);
        uniqueclientList.add(BOB);
        assertThrows(DuplicateClientException.class, () -> uniqueclientList.setclient(ALICE, BOB));
    }

    @Test
    public void remove_nullclient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueclientList.remove(null));
    }

    @Test
    public void remove_clientDoesNotExist_throwsclientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueclientList.remove(ALICE));
    }

    @Test
    public void remove_existingclient_removesclient() {
        uniqueclientList.add(ALICE);
        uniqueclientList.remove(ALICE);
        UniqueClientList expectedUniqueclientList = new UniqueClientList();
        assertEquals(expectedUniqueclientList, uniqueclientList);
    }

    @Test
    public void setclients_nullUniqueclientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueclientList.setclients((UniqueClientList) null));
    }

    @Test
    public void setclients_uniqueclientList_replacesOwnListWithProvidedUniqueclientList() {
        uniqueclientList.add(ALICE);
        UniqueClientList expectedUniqueclientList = new UniqueClientList();
        expectedUniqueclientList.add(BOB);
        uniqueclientList.setclients(expectedUniqueclientList);
        assertEquals(expectedUniqueclientList, uniqueclientList);
    }

    @Test
    public void setclients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueclientList.setclients((List<Client>) null));
    }

    @Test
    public void setclients_list_replacesOwnListWithProvidedList() {
        uniqueclientList.add(ALICE);
        List<Client> clientList = Collections.singletonList(BOB);
        uniqueclientList.setclients(clientList);
        UniqueClientList expectedUniqueclientList = new UniqueClientList();
        expectedUniqueclientList.add(BOB);
        assertEquals(expectedUniqueclientList, uniqueclientList);
    }

    @Test
    public void setclients_listWithDuplicateclients_throwsDuplicateclientException() {
        List<Client> listWithDuplicateClients = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateClientException.class, () -> uniqueclientList.setclients(listWithDuplicateClients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueclientList.asUnmodifiableObservableList().remove(0));
    }
}
