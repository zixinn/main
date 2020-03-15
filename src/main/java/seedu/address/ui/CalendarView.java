package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;


/**
 * The view for the calendar.
 */
public class CalendarView extends UiPart<Region> {

    private static final String FXML = "Calendar.fxml";

    @FXML
    private Label calendarTitle;

    @FXML
    private GridPane calendarGrid;

    /**
     * Constructs the CalendarView.
     */
    public CalendarView(String week) {
        super(FXML);

        calendarTitle.setText(String.format("Viewing: %s week", week));

        String[] daysOfWeek = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday", "Sunday"};

        for (int row = 0; row < 7; row++) {
            CalendarLabel calendarLabel = new CalendarLabel(daysOfWeek[row]);
            calendarGrid.add(calendarLabel.getRoot(), 0, row);
        }

    }
}
