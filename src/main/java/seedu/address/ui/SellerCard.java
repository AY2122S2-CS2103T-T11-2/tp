package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.property.NullPropertyToSell;
import seedu.address.model.seller.Seller;

/**
 * An UI component that displays information of a {@code client}.
 */
public class SellerCard extends UiPart<Region> {

    private static final String FXML = "sellerListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Seller seller;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private FlowPane tags;
    @FXML
    private Label appointment;
    @FXML
    private Label propertyType;
    @FXML
    private Label propertyLocation;
    @FXML
    private Label propertyLowerPrice;
    @FXML
    private Label propertyUpperPrice;
    @FXML
    private Label propertyAddress;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public SellerCard(Seller seller, int displayedIndex) {
        super(FXML);
        this.seller = seller;
        id.setText(displayedIndex + ". ");
        name.setText(seller.getName().fullName);
        phone.setText(seller.getPhone().value);
        appointment.setText(seller.getAppointment().getAppointmentDetail());
        seller.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (!(seller.getPropertyToSell() instanceof NullPropertyToSell)) {
            propertyType.setText("Type: " + seller.getPropertyToSell().getHouse().getHouseTypeToString());
            propertyLocation.setText("Location: " + seller.getPropertyToSell().getHouse().getLocationToString());
            propertyLowerPrice.setText("Price: " + seller.getPropertyToSell().getPriceRange().getLowerToString()
                                    + " to " + seller.getPropertyToSell().getPriceRange().getUpperToString());
            //propertyLowerPrice.setText(seller.getPropertyToSell().getPriceRange().getLowerToString());
            //propertyUpperPrice.setText(seller.getPropertyToSell().getPriceRange().getUpperToString());
            propertyAddress.setText("Address: " + seller.getPropertyToSell().getAddress());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BuyerCard)) {
            return false;
        }

        // state check
        SellerCard card = (SellerCard) other;
        return id.getText().equals(card.id.getText())
                && seller.equals(card.seller);
    }
}

