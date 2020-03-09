package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Label for the days of the week on the calendar.
 */
public class CalendarLabel extends UiPart<Region> {

    private static final String FXML = "CalendarLabel.fxml";

    @FXML
    private Label calendarLabel;

    public CalendarLabel(String label) {
        super(FXML);
        this.calendarLabel.setText(label);
    }
}
