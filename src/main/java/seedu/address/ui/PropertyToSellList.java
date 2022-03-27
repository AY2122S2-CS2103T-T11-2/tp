//package seedu.address.ui;
//
//import java.util.Comparator;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Region;
//import seedu.address.model.client.Client;
//import seedu.address.model.property.PropertyToSell;
//
///**
// * An UI component that displays information of a {@code client}.
// */
//public class PropertyToSellList extends UiPart<Region> {
//
//    private static final String FXML = "clientListCard.fxml";
//
//    /**
//     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
//     * As a consequence, UI elements' variable names cannot be set to such keywords
//     * or an exception will be thrown by JavaFX during runtime.
//     *
//     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
//     */
//
//    public final Client client;
//
//    @FXML
//    private Label houseType;
//    @FXML
//    private Label price;
//    @FXML
//    private Label location;
//    @FXML
//    private FlowPane tags;
//    @FXML
//    private Label appointment;
//
//    /**
//     * Creates a {@code PropertyToSellList} with the given {@code Property} and index to display.
//     */
//    public PropertyToSellList(PropertyToSell propertyToSell) {
//        super(FXML);
//        this.client = client;
//        id.setText(displayedIndex + ". ");
//        name.setText(client.getName().fullName);
//        phone.setText(client.getPhone().value);
//        appointment.setText(client.getAppointment().getAppointmentDetail());
//        client.getTags().stream()
//                .sorted(Comparator.comparing(tag -> tag.tagName))
//                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        // short circuit if same object
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof BuyerCard)) {
//            return false;
//        }
//
//        // state check
//        BuyerCard card = (BuyerCard) other;
//        return id.getText().equals(card.id.getText())
//                && client.equals(card.client);
//    }
//}
