package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.seller.Seller;

public interface ReadOnlySellerAddressBook {
    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Seller> getSellerList();

}
