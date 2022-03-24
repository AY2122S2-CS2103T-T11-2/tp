package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.seller.Seller;
import seedu.address.model.seller.UniqueSellerList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameSeller comparison)
 */
public class SellerAddressBook implements ReadOnlySellerAddressBook {

    private final UniqueSellerList sellers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        sellers = new UniqueSellerList();
    }

    public SellerAddressBook() {}

    /**
     * Creates an AddressBook using the clients in the {@code toBeCopied}
     */
    public SellerAddressBook(ReadOnlySellerAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the seller list with {@code sellers}.
     * {@code sellers} must not contain duplicate sellers.
     */
    public void setSellers(List<Seller> sellers) {
        this.sellers.setSellers(sellers);
    }

    /**
     * Resets the existing data of this {@code SellerAddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlySellerAddressBook newData) {
        requireNonNull(newData);

        setSellers(newData.getSellerList());
    }

    /**
     * Checks if the seller list contains the seller.
     *
     * @param seller The seller.
     * @return Whether seller is already in list.
     */
    public boolean hasSeller(Seller seller) {
        requireNonNull(seller);
        return sellers.contains(seller);
    }

    /**
     * Adds a new seller to the client list.
     *
     * @param seller The seller.
     */
    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedclient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedclient} must not be the same as another existing client in the address book.
     */
    public void setSeller(Seller target, Seller editedSeller) {
        requireNonNull(editedSeller);

        sellers.setSeller(target, editedSeller);
    }

    public void sortPersons() {
        sellers.sortPersons();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeSeller(Seller key) {
        sellers.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return sellers.asUnmodifiableObservableList().size() + " sellers";
        // TODO: refine later
    }

    @Override
    public ObservableList<Seller> getSellerList() {
        return sellers.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && sellers.equals(((SellerAddressBook) other).sellers));
    }

    @Override
    public int hashCode() {
        return sellers.hashCode();
    }
}
