package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import seedu.address.model.calendar.Calendar;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.ScheduledTask;
import seedu.address.model.task.Task;
import seedu.address.model.util.DailySchedulable;
import seedu.address.model.util.DailySchedulableComparator;


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
    public CalendarView(Calendar calendar, List<Task> tasks, List<Lesson> lessons) {
        super(FXML);

        if (calendar.equals(Calendar.getNowCalendar())) {
            calendarTitle.setText(String.format("Viewing: %s week", "this"));
        } else {
            calendarTitle.setText(String.format("Viewing: %s week", "next"));
        }

        String[] daysOfWeek = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday", "Sunday"};

        for (int col = 0; col < 7; col++) {
            CalendarLabel calendarLabel = new CalendarLabel(daysOfWeek[col]);
            calendarGrid.add(calendarLabel.getRoot(), col, 0);
            CalendarCardPanel calendarCardPanel = new CalendarCardPanel();
            cardPanels.add(calendarCardPanel);
            calendarGrid.add(calendarCardPanel.getRoot(), col, 1);
        }

        addCards(calendar, tasks, lessons);
    }

    /**
     * Adds cards to the calendar.
     */
    public void addCards(Calendar calendar, List<Task> tasks, List<Lesson> lessons) {
        Calendar[] calendars = calendar.getWeek();
        for (int i = 0; i < 7; i++) {
            final int cnt = i;
            CalendarCardPanel panel = cardPanels.get(i);
            Calendar c = calendars[i];
            List<DailySchedulable> items = new ArrayList<>();

            lessons.forEach(x -> {
                if (x.getDay().getValue() == (cnt + 1)) {
                    items.add(x);
                }
            });

            tasks.forEach(task -> {
                if (task instanceof ScheduledTask && c.isWithinDate(task)) {
                    items.add(task);
                }
            });

            items.sort(new DailySchedulableComparator());

            for (DailySchedulable item : items) {
                panel.addCard(new CalendarCard(item));
            }
        }
    }
}
