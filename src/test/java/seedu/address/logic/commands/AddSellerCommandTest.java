package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySellerAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Client;
import seedu.address.model.seller.Seller;
import seedu.address.testutil.SellerBuilder;

public class AddSellerCommandTest {

    @Test
    public void constructor_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSellerCommand(null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingSellerAdded modelStub = new ModelStubAcceptingSellerAdded();
        Seller validSeller = new SellerBuilder().build();

        CommandResult commandResult = new AddSellerCommand(validSeller).execute(modelStub);

        assertEquals(String.format(AddSellerCommand.MESSAGE_SUCCESS, validSeller), commandResult.getFeedbackToUser());
        assertEquals(List.of(validSeller), modelStub.clientsAdded);
    }

    @Test
    public void execute_duplicateSeller_throwsCommandException() {
        Seller validSeller = new SellerBuilder().build();
        AddSellerCommand addSellerCommand = new AddSellerCommand(validSeller);
        ModelStub modelStub = new ModelStubWithSeller(validSeller);

        assertThrows(CommandException.class, AddSellerCommand.MESSAGE_DUPLICATE_CLIENT, () -> addSellerCommand
                .execute(modelStub));
    }

    @Test
    public void equals() {
        Seller janald = new SellerBuilder().withName("Janald").build();
        Seller junheng = new SellerBuilder().withName("Junheng").build();
        AddSellerCommand addJanaldCommand = new AddSellerCommand(janald);
        AddSellerCommand addJunhengCommand = new AddSellerCommand(junheng);

        // same object -> returns true
        assertEquals(addJanaldCommand, addJanaldCommand);

        // same values -> returns true
        AddSellerCommand addJanaldCommandCopy = new AddSellerCommand(janald);
        assertEquals(addJanaldCommand, addJanaldCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addJanaldCommand);

        // null -> returns false
        assertNotEquals(null, addJanaldCommand);

        // different client -> returns false
        assertNotEquals(addJanaldCommand, addJunhengCommand);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasSeller(Seller seller) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSeller(Seller seller) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getSellerAddressBookFilePath() {
            return null;
        }

        @Override
        public void setSellerAddressBookFilePath(Path sellerAddressBookFilePath) {

        }

        @Override
        public void setSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook) {

        }

        @Override
        public ReadOnlySellerAddressBook getSellerAddressBook() {
            return null;
        }

        @Override
        public void deleteSeller(Seller target) {

        }

        @Override
        public void setSeller(Seller target, Seller editedSeller) {

        }

        @Override
        public ObservableList<Seller> getFilteredSellerList() {
            return null;
        }

        @Override
        public void updateFilteredSellerList(Predicate<Seller> predicate) {

        }
    }

    /**
     * A Model stub that contains a single seller.
     */
    private static class ModelStubWithSeller extends ModelStub {
        private final Seller seller;

        ModelStubWithSeller(Seller seller) {
            requireNonNull(seller);
            this.seller = seller;
        }

        @Override
        public boolean hasSeller(Seller seller) {
            requireNonNull(seller);
            return this.seller.isSameclient(seller);
        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private static class ModelStubAcceptingSellerAdded extends ModelStub {
        final ArrayList<Client> clientsAdded = new ArrayList<>();

        @Override
        public boolean hasSeller(Seller seller) {
            requireNonNull(seller);
            return clientsAdded.stream().anyMatch(seller::isSameclient);
        }

        @Override
        public void addSeller(Seller seller) {
            requireNonNull(seller);
            clientsAdded.add(seller);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
