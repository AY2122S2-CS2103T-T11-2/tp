package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.seller.Seller;

/**
 * A utility class containing a list of {@code client} objects to be used in tests.
 */
public class TypicalSellers {

    // Sellers who are yet to indicate any properties to sell
    public static final Seller YUQI = new SellerBuilder().withName("Alice Pauline")
            .withPhone("94351253").withAppointment("2022-05-01-12-00")
            .withTags("friends").build();
    public static final Seller SHIHONG = new SellerBuilder().withName("Benson Meier")
            .withPhone("98765432").withAppointment("2022-05-04-09-00")
            .withTags("owesMoney", "friends").build();
    public static final Seller JUNHENG = new SellerBuilder().withName("Carl Kurz")
            .withPhone("95352563").build();
    public static final Seller JUNHONG = new SellerBuilder().withName("Daniel Meier")
            .withPhone("87652533").withTags("friends").build();
    public static final Seller JANALD = new SellerBuilder().withName("Elle Meyer")
            .withPhone("9482224").build();
    public static final Seller DARA = new SellerBuilder().withName("Fiona Kunz")
            .withPhone("9482427").build();
    public static final Seller RICHARD = new SellerBuilder().withName("George Best")
            .withPhone("9482442").build();

    // Manually added
    public static final Seller HOON = new SellerBuilder().withName("Hoon Meier")
            .withPhone("8482424").build();
    public static final Seller IDA = new SellerBuilder().withName("Ida Mueller").withPhone("8482131")
            .build();

    // Manually added - client's details found in {@code CommandTestUtil}
    public static final Seller AMY = new SellerBuilder()
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withAppointment(VALID_APPOINTMENT_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Seller BOB = new SellerBuilder()
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAppointment(VALID_APPOINTMENT_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalSellers() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Seller seller : getTypicalSellers()) {
            ab.addclient(seller);
        }
        return ab;
    }

    public static List<Seller> getTypicalSellers() {
        return new ArrayList<>(Arrays.asList(JUNHENG, JUNHONG, SHIHONG, JANALD, DARA, RICHARD, YUQI));
    }
}
