package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;
import seedu.address.model.seller.Seller;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameclient comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueClientList clients;

    // private final UniqueBuyerList buyers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniqueClientList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the clients in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setclients(List<Client> clients) {
        this.clients.setclients(clients);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setclients(newData.getclientList());
    }

    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    public boolean hasclient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a client to the address book.
     * The client must not already exist in the address book.
     */
    public void addclient(Client p) {
        clients.add(p);
    }

    //======== AddBuyer =========//

    /**
     * Checks if the client list contains the buyer.
     *
     * @param buyer The buyer.
     * @return Whether buyer is already in list.
     */
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return clients.contains(buyer);
    }

    /**
     * Adds a new buyer to the client list.
     *
     * @param buyer The buyer.
     */
    public void addBuyer(Buyer buyer) {
        clients.add(buyer);
    }

    //======== AddSeller =========//

    /**
     * Checks if the client list contains the seller.
     *
     * @param seller The seller.
     * @return Whether seller is already in list.
     */
    public boolean hasSeller(Seller seller) {
        requireNonNull(seller);
        return clients.contains(seller);
    }

    /**
     * Adds a new seller to the client list.
     *
     * @param seller The seller.
     */
    public void addSeller(Seller seller) {
        clients.add(seller);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedclient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedclient} must not be the same as another existing client in the address book.
     */
    public void setclient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        clients.setclient(target, editedClient);
    }

    public void sortPersons() {
        clients.sortPersons();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeclient(Client key) {
        clients.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return clients.asUnmodifiableObservableList().size() + " clients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getclientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && clients.equals(((AddressBook) other).clients));
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
