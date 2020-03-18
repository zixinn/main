package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.facilitator.Facilitator;

/**
 * Panel containing the list of facilitators for a module.
 */
public class FacilitatorPanel extends UiPart<Region> {
    private static final String FXML = "FacilitatorPanel.fxml";

    @FXML
    private ListView<Facilitator> facilitatorsView;

    public FacilitatorPanel(ObservableList<Facilitator> facilitatorList) {
        super(FXML);
        facilitatorsView.setItems(facilitatorList);
        facilitatorsView.setCellFactory(listView -> new FacilitatorListViewCell());
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
                setGraphic(new FacilitatorModCard(facilitator, getIndex() + 1).getRoot());
            }
        }
    }

}
