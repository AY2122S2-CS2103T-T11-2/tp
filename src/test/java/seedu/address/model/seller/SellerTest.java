package seedu.address.model.seller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSellers.BOB;
import static seedu.address.testutil.TypicalSellers.YUQI;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToSell;
import seedu.address.testutil.SellerBuilder;


public class SellerTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Seller seller = new SellerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> seller.getTags().remove(0));
    }

    @Test
    public void isSameSeller() {
        // same object -> returns true
        assertTrue(YUQI.isSameclient(YUQI));

        // null -> returns false
        assertFalse(YUQI.isSameclient(null));

        // same name, all other attributes different -> returns true
        Seller editedYuqi = new SellerBuilder(YUQI).withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(YUQI.isSameclient(editedYuqi));

        // different name, all other attributes same -> returns false
        editedYuqi = new SellerBuilder(YUQI).withName(VALID_NAME_BOB).build();
        assertFalse(YUQI.isSameclient(editedYuqi));

        // name differs in case, all other attributes same -> returns false
        Seller editedBob = new SellerBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameclient(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new SellerBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameclient(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Seller yuqiCopy = new SellerBuilder(YUQI).build();
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
        Seller editedYuqi = new SellerBuilder(YUQI).withName(VALID_NAME_BOB).build();
        assertNotEquals(YUQI, editedYuqi);

        // different phone -> returns false
        editedYuqi = new SellerBuilder(YUQI).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(YUQI, editedYuqi);

        // different tags -> returns false
        editedYuqi = new SellerBuilder(YUQI).withTags(VALID_TAG_HUSBAND).build();
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

        String a1 = "Serangoon Avenue 3 Block 201";
        String a2 = "Rosyth Road 546208";
        String a3 = "61 Bishan Street 21";

        pr1 = new PriceRange(100000, 200000);
        pr2 = new PriceRange(200000, 300000);
        pr3 = new PriceRange(300000, 400000);

        PropertyToSell ptb1 = new PropertyToSell(h1, pr1, a1);
        PropertyToSell ptb2 = new PropertyToSell(h2, pr2, a2);
        PropertyToSell ptb3 = new PropertyToSell(h3, pr3, a3);

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

        String a1 = "Serangoon Avenue 3 Block 201";
        String a2 = "Rosyth Road 546208";
        String a3 = "61 Bishan Street 21";

        pr1 = new PriceRange(100000, 200000);
        pr2 = new PriceRange(200000, 300000);
        pr3 = new PriceRange(300000, 400000);

        PropertyToSell pts1 = new PropertyToSell(h1, pr1, a1);
        PropertyToSell pts2 = new PropertyToSell(h2, pr2, a2);
        PropertyToSell pts3 = new PropertyToSell(h3, pr3, a3);

    }
}
