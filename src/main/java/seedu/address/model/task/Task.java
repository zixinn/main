package seedu.address.model.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task in Mod Manager. A Task in Mod Manager is strictly composed in a Module.
 */
public class Task implements TaskInterface {
    protected String description;
    protected boolean isDone;
    protected LocalDate taskDate;
    protected LocalTime taskTime;

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

    public Task(String description, String taskDate) {
        this.description = description;
        this.taskDate = LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Task(String description, String taskDate, String taskTime) {
        this.description = description;
        this.taskDate = LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.taskTime = LocalTime.parse(taskTime, DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Retrieves the description of our Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * checks if our Task is completed.
     *
     * @return boolean to indicate whether our Task is completed.
     */
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * Marks our Task as completed.
     * returns true if the Task is marked as done, false if the task has already been marked as done
     */
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
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean hasDate() {
        return taskDate != null;
    }

    /**
     * outputs the Date of the Task (day/month/year) in human readable form.
     */
    public String getDateOutput() {
        return taskDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public boolean hasTime() {
        return taskTime != null;
    }

    /**
     * outputs the Time of the Task (hour:minute) in human readable form.
     */
    public String getTimeOutput() {
        return taskTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * outputs the Date in the week of the task
     */
    public String getDateInWeek() {
        DayOfWeek dayInWeek = taskDate.getDayOfWeek();
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
        return "[" + getStatusIcon() + "]" + " " + description + "\n"
                + getDateInWeek() + ", " + getDateOutput() + ", " + "at: " + getTimeOutput() + "\n";
    }

    /**
     * returns a String representation of a Task instance in our database.
     *
     * @return String a Task representation in our database
     */
    public String toDatabase() {
        int isDoneInt = (isDone) ? 1 : 0;
        return isDoneInt + " | " + description + " | " + getDateOutput() + " | " +  getTimeOutput();
    }

    /**
     * Compares the two Deadlines for order.
     * Nearer deadlines are considered smaller
     */
    @Override
    public int compareTo(Object o) {
        assert (o instanceof Task); // can only compare between deadlines
        return 0; // to be implemented. What if the task has no date?
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

        return otherTask.getDescription().equals(getDescription())
                && otherTask.getDateOutput().equals(getDateOutput())
                && otherTask.getTimeOutput().equals(getTimeOutput());
    }
}

