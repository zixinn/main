package seedu.address.model.task.util;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Represents a Module's taskTime in Mod Manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskTime(String)}
 */
public class TaskDateTime implements Comparable {
    public static final String MESSAGE_CONSTRAINTS =
            "Task can take any values, and it should not be more than 64 characters";

    /*
     * The first character of the taskTime must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*"; // to impose stricter requirements!!

    private static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy[ HH:mm]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    public final LocalDateTime taskTime;


    /**
     * Constructs a {@code LocalDateTime}.
     *
     * @param date A valid date.
     */
    public TaskDateTime(String date) throws ParseException {
        checkArgument(date == null || isValidTaskTime(date), MESSAGE_CONSTRAINTS);
        try {
            this.taskTime = LocalDate.parse(date, dateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
        } catch (DateTimeParseException dateError) {
            throw new ParseException(String.format("Invalid date: %s.", date));
        }
    }

    /**
     * Constructs a {@code LocalDateTime}.
     *
     * @param date A valid date.
     * @param timeInDay time period in day
     */
    public TaskDateTime(String date, String timeInDay) throws ParseException {
        checkArgument(date == null || isValidTaskTime(date), MESSAGE_CONSTRAINTS);
        LocalTime timeInTheDay = null;
        try {
            timeInTheDay = LocalTime.parse(timeInDay, dateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException timeError) {
            throw new ParseException(String.format("Invalid task time: %s.", timeInDay));
        }
        try {
            this.taskTime = LocalDateTime.of(LocalDate.parse(date, dateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    timeInTheDay);
        } catch (DateTimeParseException dateError) {
            throw new ParseException(String.format("Invalid date: %s.", date));
        }
    }

    /**
     * Returns true if a given string is a valid taskTime.
     */
    public static boolean isValidTaskTime(String test) {
        // to have further check on this!
        return test.matches(VALIDATION_REGEX) && test.length() <= 64;
    }

    public LocalTime toLocalTime() {
        return taskTime.toLocalTime();
    }

    /**
     * outputs the Date in the week of the task
     */
    protected String getDateInWeek() {
        if (taskTime == null) {
            return "";
        }
        DayOfWeek dayInWeek = taskTime.getDayOfWeek();
        String title = dayInWeek.toString();
        return title.charAt(0) + title.substring(1).toLowerCase();
    }

    public LocalDateTime getLocalDateTime() {
        return taskTime;
    }

    /**
     * Compares the two Deadlines for order.
     * Nearer deadlines are considered smaller
     */
    @Override
    public int compareTo(Object other) {
        assert (other instanceof TaskDateTime); // can only compare between Tasks
        TaskDateTime otherTask = (TaskDateTime) other;
        if (taskTime.isAfter(otherTask.taskTime)) {
            return 1;
        } else if (taskTime.isBefore(otherTask.taskTime)) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        if (taskTime == null) {
            return "";
        }
        // note for Command
        // prevent users from creating 00:00 time. For this time period, the user should create an all day Task instead.
        if (taskTime.isEqual(taskTime.toLocalDate().atStartOfDay())) { // no time in day
            return taskTime.format(dateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return taskTime.format(dateTimeFormatter.ofPattern("dd/MM/yyyy[ HH:mm]"));
    }

    @Override
    public int hashCode() {
        return taskTime != null ? taskTime.hashCode() : 0;
    }
}
