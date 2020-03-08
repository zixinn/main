package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.facilitator.Facilitator;

/**
 * Panel containing the list of facilitators.
 */
public class FacilitatorListPanel extends UiPart<Region> {
    private static final String FXML = "FacilitatorListPanel.fxml";

    @FXML
    private ListView<Facilitator> facilitatorListView;

    public FacilitatorListPanel(ObservableList<Facilitator> facilitatorList) {
        super(FXML);
        facilitatorListView.setItems(facilitatorList);
        facilitatorListView.setCellFactory(listView -> new FacilitatorListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Facilitator} using a {@code FacilitatorCard}.
     */
    class FacilitatorListViewCell extends ListCell<Facilitator> {
        @Override
        protected void updateItem(Facilitator facilitator, boolean empty) {
            super.updateItem(facilitator, empty);

            if (empty || facilitator == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FacilitatorCard(facilitator, getIndex() + 1).getRoot());
            }
        }
    }

}
