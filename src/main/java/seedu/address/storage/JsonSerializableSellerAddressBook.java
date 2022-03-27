package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlySellerAddressBook;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.seller.Seller;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "sellerAddressbook")
class JsonSerializableSellerAddressBook {

    public static final String MESSAGE_DUPLICATE_SELLER = "seller list contains duplicate seller(s).";

    private final List<JsonAdaptedSeller> sellers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given sellers.
     */
    @JsonCreator
    public JsonSerializableSellerAddressBook(@JsonProperty("sellers") List<JsonAdaptedSeller> sellers) {
        this.sellers.addAll(sellers);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSellerAddressBook}.
     */
    public JsonSerializableSellerAddressBook(ReadOnlySellerAddressBook source) {
        sellers.addAll(source.getSellerList().stream().map(JsonAdaptedSeller::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SellerAddressBook toModelType() throws IllegalValueException {
        SellerAddressBook addressBook = new SellerAddressBook();
        for (JsonAdaptedSeller jsonAdaptedSeller : sellers) {
            Seller seller = jsonAdaptedSeller.toModelType();
            if (addressBook.hasSeller(seller)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SELLER);
            }
            addressBook.addSeller(seller);
        }
        return addressBook;
    }

}

