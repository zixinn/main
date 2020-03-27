package seedu.address.model.task;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.task.util.TaskIDManager;
import seedu.address.model.util.Description;

/**
 * Represents a Task that does not take in any Date or Time.
 */
public class NonScheduledTask extends Task {
    private Description description;
    private ModuleCode moduleCode;
    private boolean isDone;
    private int taskID;

    protected NonScheduledTask(Description description, ModuleCode moduleCode) {
        this.description = description;
        this.moduleCode = moduleCode;
        this.taskID = TaskIDManager.getID(moduleCode);
        TaskIDManager.addID(moduleCode, taskID);
    }

    protected NonScheduledTask(Description description, ModuleCode moduleCode, int taskID) {
        this.description = description;
        this.moduleCode = moduleCode;
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
    public boolean isSameTask(Task other) {
        if (other instanceof ScheduledTask) {
            return false;
        }

        return this.description.equals(other.getDescription())
                && this.moduleCode.equals(other.getModuleCode());
    }

    @Override
    public int getTaskID() {
        return this.taskID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, moduleCode, isDone);
    }

    @Override
    public String toString() {
        String modShow = String.format("[%s %d]", moduleCode.toString(), taskID);
        return "[" + getStatusIcon() + "]" + " " + modShow + description.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof NonScheduledTask)) {
            return false;
        }

        NonScheduledTask e = (NonScheduledTask) o;

        return this.moduleCode.equals(e.moduleCode)
                && this.taskID == e.taskID;
    }
}
