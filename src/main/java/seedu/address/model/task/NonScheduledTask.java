package seedu.address.model.task;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.util.Description;

/**
 * Represents a Task that does not take in any Date or Time.
 */
public class NonScheduledTask extends Task {
    private Description description;
    private Optional<ModuleCode> moduleCode;
    private boolean isDone;

    public NonScheduledTask(Description description, ModuleCode moduleCode) {
        this.description = description;
        this.moduleCode = Optional.of(moduleCode);
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
    public Optional<ModuleCode> getModuleCode() {
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
    public int hashCode() {
        return Objects.hash(description, moduleCode, isDone);
    }

    @Override
    public String toString() {
        String modShow = moduleCode.map(code -> " [" + code.toString() + "] ").orElse("");
        return "[" + getStatusIcon() + "]" + " " + modShow + description.toString();
    }
}
