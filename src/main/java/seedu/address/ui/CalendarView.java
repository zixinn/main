package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;



/**
 * The view for the calendar.
 */
public class CalendarView extends UiPart<Region> {

    private static final String FXML = "Calendar.fxml";

    private List<CalendarCardPanel> cardPanels = new ArrayList<>();

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
            CalendarCardPanel calendarCardPanel = new CalendarCardPanel();
            cardPanels.add(calendarCardPanel);
            calendarGrid.add(calendarCardPanel.getRoot(), 1, row);
        }
        for (int row = 0; row < 7; row++) {
            addDummyCard(row);
        }



    }

    /**
     * Adds dummy card to the calendar panel at a specific index of the week.
     *
     * @param indexOfWeek the specified index of the week.
     */
    public void addDummyCard(int indexOfWeek) {
        CalendarCard calendarCard = new CalendarCard();
        CalendarCardPanel panel = cardPanels.get(indexOfWeek);
        panel.addCard(calendarCard);
        if (indexOfWeek % 2 == 0) {
            CalendarCard card2 = new CalendarCard();
            panel.addCard(card2);
            CalendarCard card3 = new CalendarCard();
            panel.addCard(card3);

        }
        RowConstraints rowConstraints = calendarGrid.getRowConstraints().get(indexOfWeek);
        rowConstraints.setPrefHeight(panel.getHeight() + 50);

    }
}
