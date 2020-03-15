package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * An UI component that displays information of a class or task.
 */
public class CalendarCard extends UiPart<Region> {

    private static final String FXML = "CalendarCard.fxml";

    @FXML
    private StackPane cardPane;

    public CalendarCard() {
        super(FXML);
    }

}
