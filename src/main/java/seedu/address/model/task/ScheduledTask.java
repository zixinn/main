package seedu.address.model.task;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.util.Description;

/**
 * Task with a Date and Time (Hours)
 */
public class ScheduledTask extends Task {
    private Description description;
    private ModuleCode moduleCode;
    private TaskDateTime taskDateTime;
    private boolean isDone;

    protected ScheduledTask(Description description, TaskDateTime taskDateTime, ModuleCode moduleCode) {
        this.description = description;
        this.moduleCode = moduleCode;
        this.taskDateTime = taskDateTime;
        this.isDone = false;
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
        return Optional.empty();
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
    public boolean isSameTask(Task obj) {
        if (obj instanceof NonScheduledTask) {
            return false;
        }

        ScheduledTask other = (ScheduledTask) obj;

        return this.description.equals(other.getDescription())
                && this.taskDateTime.equals(other.taskDateTime)
                && this.moduleCode.equals(other.getModuleCode());
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
        String modShow = " [" + moduleCode.toString() + "] ";
        return "[" + getStatusIcon() + "]" + " " + modShow + description.toString()
                + " " + taskDateTime.toString();
    }
}
