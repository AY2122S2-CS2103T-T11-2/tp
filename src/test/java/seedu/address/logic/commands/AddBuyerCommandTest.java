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
import seedu.address.testutil.BuyerBuilder;

public class AddBuyerCommandTest {

    @Test
    public void constructor_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBuyerCommand(null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingBuyerAdded modelStub = new ModelStubAcceptingBuyerAdded();
        Buyer validBuyer = new BuyerBuilder().build();

        CommandResult commandResult = new AddBuyerCommand(validBuyer).execute(modelStub);

        assertEquals(String.format(AddBuyerCommand.MESSAGE_SUCCESS, validBuyer), commandResult.getFeedbackToUser());
        assertEquals(List.of(validBuyer), modelStub.clientsAdded);
    }

    @Test
    public void execute_duplicateBuyer_throwsCommandException() {
        Buyer validBuyer = new BuyerBuilder().build();
        AddBuyerCommand addBuyerCommand = new AddBuyerCommand(validBuyer);
        ModelStub modelStub = new ModelStubWithBuyer(validBuyer);

        assertThrows(CommandException.class, AddBuyerCommand.MESSAGE_DUPLICATE_CLIENT, () -> addBuyerCommand
                .execute(modelStub));
    }

    @Test
    public void equals() {
        Buyer janald = new BuyerBuilder().withName("Janald").build();
        Buyer junheng = new BuyerBuilder().withName("Junheng").build();
        AddBuyerCommand addJanaldCommand = new AddBuyerCommand(janald);
        AddBuyerCommand addJunhengCommand = new AddBuyerCommand(junheng);

        // same object -> returns true
        assertEquals(addJanaldCommand, addJanaldCommand);

        // same values -> returns true
        AddBuyerCommand addJanaldCommandCopy = new AddBuyerCommand(janald);
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
     * A Model stub that contains a single buyer.
     */
    private static class ModelStubWithBuyer extends ModelStub {
        private final Buyer buyer;

        ModelStubWithBuyer(Buyer buyer) {
            requireNonNull(buyer);
            this.buyer = buyer;
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return this.buyer.isSameclient(buyer);
        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private static class ModelStubAcceptingBuyerAdded extends ModelStub {
        final ArrayList<Client> clientsAdded = new ArrayList<>();

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return clientsAdded.stream().anyMatch(buyer::isSameclient);
        }

        @Override
        public void addBuyer(Buyer buyer) {
            requireNonNull(buyer);
            clientsAdded.add(buyer);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
