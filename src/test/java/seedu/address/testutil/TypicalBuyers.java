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
import seedu.address.model.buyer.Buyer;

/**
 * A utility class containing a list of {@code client} objects to be used in tests.
 */
public class TypicalBuyers {

    // Buyers who are yet to indicate any properties to buy
    public static final Buyer YUQI = new BuyerBuilder().withName("Alice Pauline")
            .withPhone("94351253").withAppointment("2022-05-01-12-00")
            .withTags("friends").build();
    public static final Buyer SHIHONG = new BuyerBuilder().withName("Benson Meier")
            .withPhone("98765432").withAppointment("2022-05-04-09-00")
            .withTags("owesMoney", "friends").build();
    public static final Buyer JUNHENG = new BuyerBuilder().withName("Carl Kurz")
            .withPhone("95352563").build();
    public static final Buyer JUNHONG = new BuyerBuilder().withName("Daniel Meier")
            .withPhone("87652533").withTags("friends").build();
    public static final Buyer JANALD = new BuyerBuilder().withName("Elle Meyer")
            .withPhone("9482224").build();
    public static final Buyer DARA = new BuyerBuilder().withName("Fiona Kunz")
            .withPhone("9482427").build();
    public static final Buyer RICHARD = new BuyerBuilder().withName("George Best")
            .withPhone("9482442").build();

    // Manually added
    public static final Buyer HOON = new BuyerBuilder().withName("Hoon Meier")
            .withPhone("8482424").build();
    public static final Buyer IDA = new BuyerBuilder().withName("Ida Mueller").withPhone("8482131").build();

    // Manually added - client's details found in {@code CommandTestUtil}
    public static final Buyer AMY = new BuyerBuilder()
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withAppointment(VALID_APPOINTMENT_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Buyer BOB = new BuyerBuilder()
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAppointment(VALID_APPOINTMENT_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBuyers() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Buyer buyer : getTypicalBuyers()) {
            ab.addclient(buyer);
        }
        return ab;
    }

    public static List<Buyer> getTypicalBuyers() {
        return new ArrayList<>(Arrays.asList(JUNHENG, JUNHONG, SHIHONG, JANALD, DARA, RICHARD, YUQI));
    }
}
