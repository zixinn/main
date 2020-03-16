package seedu.address.model.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import seedu.address.model.module.Module;

/**
 * Represents a Task in Mod Manager. A Task in Mod Manager is strictly composed in a Module.
 */
public class Task implements TaskInterface {
    private static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy[ HH:mm]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    protected Description description;
    protected boolean isDone;
    protected LocalDateTime taskTime;


    /**
     * Creates a new Task with {@code description} associated with a Module.
     * The Task is assumed to be uncompleted when created.
     *
     * @param description the description/details of our task
     */
    public Task(String description) {
        this.description = new Description(description);
        this.isDone = false;
    }

    public Task(String description, String date) {
        this.description = new Description(description);
        this.taskTime = LocalDate.parse(date, dateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
    }

    public Task(String description, String date, String timeInDay) {
        this.description = new Description(description);
        LocalTime timeInTheDay = LocalTime.parse(timeInDay, dateTimeFormatter.ofPattern("HH:mm"));
        this.taskTime = LocalDateTime.of(LocalDate.parse(date, dateTimeFormatter.ofPattern("dd/MM/yyyy")),
                timeInTheDay);
    }

    /**
     * Retrieves the description of our Task.
     */
    @Override
    public String getDescription() {
        return description.toString();
    }

    /**
     * checks if our Task is completed.
     *
     * @return boolean to indicate whether our Task is completed.
     */
    @Override
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * Marks our Task as completed.
     * returns true if the Task is marked as done, false if the task has already been marked as done
     */
    @Override
    public boolean markAsDone() {
        if (isTaskDone()) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    /**
     * Gets the status icon of our Task.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    protected LocalDateTime getTime() {
        return taskTime;
    }

    protected boolean isTimeAvailable() {
        return taskTime != null;
    }

    /**
     * outputs the Time of the Task (hour:minute) in human readable form.
     */
    public String getTimeOutput() {
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

    /**
     * returns a String representation of a Task instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        String time = isTimeAvailable() ? " " + getTimeOutput() + "\n" : "\n";
        return "[" + getStatusIcon() + "]" + " " + description
                + time;
    }

    /**
     * returns a String representation of a Task instance in our database.
     *
     * @return String a Task representation in our database
     */
    protected String toDatabase() {
        int isDoneInt = (isDone) ? 1 : 0;
        return isDoneInt + " | " + description + " | " + getTimeOutput() + " | " + getTimeOutput();
    }

    /**
     * Returns true if both tasks are the same
     *
     * @param otherTask The other task to compare with.
     * @return true if both tasks have the same description and time slot
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getTime().equals(getTime());
    }
    /**
     * Compares the two Deadlines for order.
     * Nearer deadlines are considered smaller
     */
    @Override
    public int compareTo(Object other) {
        assert (other instanceof Task); // can only compare between Tasks
        Task otherTask = (Task) other;
        if (taskTime.isAfter(otherTask.getTime())) {
            return 1;
        } else if (taskTime.isBefore(otherTask.getTime())) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;

        return otherTask.getDescription().equals(getDescription()) // same definition
                && otherTask.compareTo(this) == 0; // same time
    }
}

