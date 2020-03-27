package seedu.address.model.task;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.task.util.TaskIDManager;
import seedu.address.model.util.Description;

import static java.util.Objects.requireNonNull;

/**
 * Task with a Date and Time (Hours)
 */
public class ScheduledTask extends Task {
    private Description description;
    private ModuleCode moduleCode;
    private TaskDateTime taskDateTime;
    private boolean isDone;
    private int taskID;

    protected ScheduledTask(Description description, TaskDateTime taskDateTime, ModuleCode moduleCode) {
        this.description = description;
        this.moduleCode = moduleCode;
        this.taskDateTime = taskDateTime;
        this.isDone = false;
        this.taskID = TaskIDManager.getID(moduleCode);
        TaskIDManager.addID(moduleCode, taskID);
    }

    protected ScheduledTask(Description description, TaskDateTime taskDateTime, ModuleCode moduleCode, int taskID) {
        this.description = description;
        this.moduleCode = moduleCode;
        this.taskDateTime = taskDateTime;
        this.isDone = false;
        this.taskID = taskID;
        TaskIDManager.addID(moduleCode, taskID);
    }

    /**
     * Gets the status icon of our Task.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String getTimeString() {
        return taskDateTime.toString();
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    @Override
    public Optional<TaskDateTime> getTaskDateTime() {
        return Optional.of(taskDateTime);
    }

    @Override
    public boolean isTaskDone() {
        return isDone;
    }

    @Override
    public boolean markAsDone() {
        boolean old = isDone;
        isDone = true;
        return !old;
    }

    @Override
    public boolean isSameTask(Task other) {
        if (!(other instanceof ScheduledTask)) {
            return false;
        }

        assert other.getTaskDateTime().isPresent();

        return this.description.equals(other.getDescription())
                && this.taskDateTime.equals(other.getTaskDateTime().get())
                && this.moduleCode.equals(other.getModuleCode())
                && this.taskID == other.getTaskID();
    }

    @Override
    public int getTaskID() {
        return this.taskID;
    }

    @Override
    public Optional<LocalTime> getComparableTime() {
        return Optional.of(taskDateTime.getLocalDateTime().toLocalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, taskDateTime, moduleCode, isDone);
    }

    @Override
    public String toString() {
        String modShow = String.format("[%s %d]", moduleCode.toString(), taskID);
        return "[" + getStatusIcon() + "]" + " " + modShow + description.toString()
                + " " + taskDateTime.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ScheduledTask)) {
            return false;
        }

        ScheduledTask e = (ScheduledTask) o;

        return this.moduleCode.equals(e.moduleCode)
                && this.taskID == e.taskID;
    }
}
