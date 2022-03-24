package seedu.address.model.buyer;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.BOB;
import static seedu.address.testutil.TypicalBuyers.YUQI;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.testutil.BuyerBuilder;


public class BuyerTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Buyer buyer = new BuyerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> buyer.getTags().remove(0));
    }

    @Test
    public void isSameBuyer() {
        // same object -> returns true
        assertTrue(YUQI.isSameclient(YUQI));

        // null -> returns false
        assertFalse(YUQI.isSameclient(null));

        // same name, all other attributes different -> returns true
        Buyer editedYuqi = new BuyerBuilder(YUQI).withPhone(VALID_PHONE_BOB)
                    .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(YUQI.isSameclient(editedYuqi));

        // different name, all other attributes same -> returns false
        editedYuqi = new BuyerBuilder(YUQI).withName(VALID_NAME_BOB).build();
        assertFalse(YUQI.isSameclient(editedYuqi));

        // name differs in case, all other attributes same -> returns false
        Buyer editedBob = new BuyerBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameclient(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new BuyerBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameclient(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Buyer yuqiCopy = new BuyerBuilder(YUQI).build();
        assertEquals(YUQI, yuqiCopy);

        // same object -> returns true
        assertEquals(YUQI, YUQI);

        // null -> returns false
        assertNotEquals(null, YUQI);

        // different type -> returns false
        assertNotEquals(5, YUQI);

        // different client -> returns false
        assertNotEquals(YUQI, BOB);

        // different name -> returns false
        Buyer editedYuqi = new BuyerBuilder(YUQI).withName(VALID_NAME_BOB).build();
        assertNotEquals(YUQI, editedYuqi);

        // different phone -> returns false
        editedYuqi = new BuyerBuilder(YUQI).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(YUQI, editedYuqi);

        // different tags -> returns false
        editedYuqi = new BuyerBuilder(YUQI).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(YUQI, editedYuqi);


        PriceRange pr1;
        PriceRange pr2;
        PriceRange pr3;

        String location1 = "Serangoon";
        String location2 = "Kovan";
        String location3 = "Bishan";

        HouseType ht1 = HouseType.HDB_FLAT;
        HouseType ht2 = HouseType.BUNGALOW;
        HouseType ht3 = HouseType.APARTMENT;

        House h1 = new House(ht1, location1);
        House h2 = new House(ht2, location2);
        House h3 = new House(ht3, location3);

        pr1 = new PriceRange(100000, 200000);
        pr2 = new PriceRange(200000, 300000);
        pr3 = new PriceRange(300000, 400000);

        PropertyToBuy ptb1 = new PropertyToBuy(h1, pr1);
        PropertyToBuy ptb2 = new PropertyToBuy(h2, pr2);
        PropertyToBuy ptb3 = new PropertyToBuy(h3, pr3);

        // Same list of properties with different order
        editedYuqi = new BuyerBuilder(YUQI).withProperties(ptb1, ptb2, ptb3).build();
        Buyer testYuqi = new BuyerBuilder(YUQI).withProperties(ptb2, ptb3, ptb1).build();
        assertEquals(editedYuqi, testYuqi);

        // Same list of properties with same order
        editedYuqi = new BuyerBuilder(YUQI).withProperties(ptb1, ptb2, ptb3).build();
        testYuqi = new BuyerBuilder(YUQI).withProperties(ptb1, ptb2, ptb3).build();
        assertEquals(editedYuqi, testYuqi);

        // Different list of properties
        editedYuqi = new BuyerBuilder(YUQI).withProperties(ptb1).build();
        testYuqi = new BuyerBuilder(YUQI).withProperties(ptb1, ptb2, ptb3).build();
        assertNotEquals(editedYuqi, testYuqi);

    }

    @Test
    public void hasSameProperties() {
        PriceRange pr1;
        PriceRange pr2;
        PriceRange pr3;

        String location1 = "Kent Ridge";
        String location2 = "Tampines";
        String location3 = "Bishan";

        HouseType ht1 = HouseType.HDB_FLAT;
        HouseType ht2 = HouseType.BUNGALOW;
        HouseType ht3 = HouseType.APARTMENT;

        House h1 = new House(ht1, location1);
        House h2 = new House(ht2, location2);
        House h3 = new House(ht3, location3);

        pr1 = new PriceRange(100000, 200000);
        pr2 = new PriceRange(200000, 300000);
        pr3 = new PriceRange(300000, 400000);


        PropertyToBuy ptb1 = new PropertyToBuy(h1, pr1);
        PropertyToBuy ptb2 = new PropertyToBuy(h2, pr2);
        PropertyToBuy ptb3 = new PropertyToBuy(h3, pr3);

        Buyer testBuyer = new BuyerBuilder().withProperties(ptb1).build();
        Buyer testBuyer2 = new BuyerBuilder().withProperties(ptb1, ptb2).build();
        Buyer testBuyer3 = new BuyerBuilder(testBuyer2).build();
        Buyer testBuyer4 = new BuyerBuilder().withProperties(ptb2, ptb1).build();

        assertTrue(testBuyer2.hasSameProperties(testBuyer3));
        assertFalse(testBuyer.hasSameProperties(testBuyer2));
        assertTrue(testBuyer3.hasSameProperties(testBuyer4));

    }
}
