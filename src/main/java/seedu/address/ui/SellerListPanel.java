package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.seller.Seller;

/**
 * Panel containing the list of clients.
 */
public class SellerListPanel extends UiPart<Region> {
    private static final String FXML = "sellerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SellerListPanel.class);

    @FXML
    private ListView<Seller> sellerListView;

    /**
     * Creates a {@code clientListPanel} with the given {@code ObservableList}.
     */
    public SellerListPanel(ObservableList<Seller> sellerList) {
        super(FXML);
        sellerListView.setItems(sellerList);
        sellerListView.setCellFactory(listView -> new SellerListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code client} using a {@code clientCard}.
     */
    class SellerListViewCell extends ListCell<Seller> {
        @Override
        protected void updateItem(Seller seller, boolean empty) {
            super.updateItem(seller, empty);

            if (empty || seller == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SellerCard(seller, getIndex() + 1).getRoot());
            }
        }
    }

}
