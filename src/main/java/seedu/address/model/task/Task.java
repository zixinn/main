package seedu.address.model.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task in Mod Manager. A Task in Mod Manager is strictly composed in a Module.
 */
public class Task implements TaskInterface {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime taskTime;

    /**
     * Creates a new Task with {@code description} associated with a Module.
     * The Task is assumed to be uncompleted when created.
     *
     * @param description the description/details of our task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String date) {
        this.description = description;
        this.taskTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
    }

    public Task(String description, String date, String timeInDay) {
        this.description = description;
        LocalTime timeInTheDay = LocalTime.parse(timeInDay, DateTimeFormatter.ofPattern("HH:mm"));
        this.taskTime = LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                timeInTheDay);
    }

    /**
     * Retrieves the description of our Task.
     */
    @Override
    public String getDescription() {
        return description;
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
        return taskTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * outputs the Date in the week of the task
     */
    protected String getDateInWeek() {
        DayOfWeek dayInWeek = taskTime.getDayOfWeek();
        String title = dayInWeek.toString();
        return title.charAt(0) + title.substring(1);
    }

    /**
     * returns a String representation of a Task instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        String time = isTimeAvailable() ? getTimeOutput() + "\n" : "";
        return "[" + getStatusIcon() + "]" + " " + description + "\n"
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

