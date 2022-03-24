package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySellerAddressBook;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Appointment NO_APPOINTMENT = new Appointment("");

    public static Client[] getSampleclients() {
        return new Client[] {
            new Client(new Name("Alex Yeoh"), new Phone("87438807"),
                NO_APPOINTMENT, getTagSet("friends")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), NO_APPOINTMENT,
                getTagSet("colleagues", "friends")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"), NO_APPOINTMENT,
                getTagSet("neighbours")),
            new Client(new Name("David Li"), new Phone("91031282"), NO_APPOINTMENT,
                getTagSet("family")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), NO_APPOINTMENT,
                getTagSet("classmates")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), NO_APPOINTMENT,
                getTagSet("colleagues"))
        };
    }

    public static Seller[] getSampleSellers() {
        return new Seller[] {
            new Seller(new Name("Jacky"), new Phone("2103"),
            NO_APPOINTMENT, getTagSet("friends"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleclients()) {
            sampleAb.addclient(sampleClient);
        }
        return sampleAb;
    }

    public static ReadOnlySellerAddressBook getSampleSellerAddressBook() {
        SellerAddressBook sampleSab = new SellerAddressBook();
        for (Seller sampleSeller : getSampleSellers()) {
            sampleSab.addSeller(sampleSeller);
        }
        return sampleSab;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
