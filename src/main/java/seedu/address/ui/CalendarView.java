package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import seedu.address.model.calendar.Calendar;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonType;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDateTime;
import seedu.address.model.util.DailySchedulableComparator;
import seedu.address.model.util.DailySchedulableInterface;
import seedu.address.model.util.Description;

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
    public CalendarView(String week, List<Task> tasks, List<Lesson> lessons) {
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

        addCards(week, tasks, lessons);
    }

    /**
     * Adds cards to the calendar.
     */
    public void addCards(String week, List<Task> tasks, List<Lesson> lessons) {
        Calendar calendar;
        if (week.equals("this")) {
            calendar = Calendar.getNowCalendar();
        } else {
            calendar = Calendar.getNextWeekCalendar();
        }
        Calendar[] calendars = calendar.getWeek();
        for (int i = 0; i < 7; i++) {
            final int cnt = i;
            CalendarCardPanel panel = cardPanels.get(i);
            Calendar c = calendars[i];
            List<DailySchedulableInterface> items = new ArrayList<>();

            lessons.forEach(x -> {
                if (x.getDay().getValue() == cnt) {
                    items.add(x);
                }
            });

            tasks.forEach(x -> {
                if (c.isWithinDate(x)) {
                    items.add(x);
                }
            });

            items.sort(new DailySchedulableComparator());

            for (DailySchedulableInterface item : items) {
                panel.addCard(new CalendarCard(item));
            }

            RowConstraints rowConstraints = calendarGrid.getRowConstraints().get(i);
            rowConstraints.setPrefHeight(panel.getHeight() + 50);
        }

    }

    /**
     * Adds dummy cards to the calendar panel.
     * This method will be removed in the future.
     */
    public void addDummyCard(String week) {
        Calendar now = Calendar.getNowCalendar();
        List<Lesson> lessons = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ModuleCode code = new ModuleCode(String.format("CS210%d", i));
            LocalTime startTime = LocalTime.parse("14:00");
            LocalTime endTime = LocalTime.parse("16:00");
            LocalDate date = now.getLocalDate().plusDays(i);
            Lesson lesson = new Lesson(code, LessonType.LAB, date.getDayOfWeek(), startTime, endTime);
            lessons.add(lesson);
            tasks.add(new Task(new Description("read"),
                    new TaskDateTime(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));
        }
        Task task = new Task(new Description("HW"), new TaskDateTime("15/03/2020", "18:00"));
        tasks.add(task);
        addCards(week, tasks, lessons);
    }
}
