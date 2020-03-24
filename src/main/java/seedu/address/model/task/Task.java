package seedu.address.model.task;

import java.time.LocalTime;
import java.util.Optional;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.util.DailySchedulable;
import seedu.address.model.util.Description;

/**
 * Interface for Task class.
 */
public abstract class Task implements DailySchedulable {
    public abstract Description getDescription();
    public abstract ModuleCode getModuleCode();
    public abstract Optional<TaskDateTime> getTaskDateTime();
    public abstract boolean isTaskDone();
    public abstract boolean markAsDone();
    public abstract boolean isSameTask(Task other);

    public static ScheduledTask makeScheduledTask(Description description,
                                           TaskDateTime taskDateTime,
                                           ModuleCode moduleCode) {
        return new ScheduledTask(description, taskDateTime, moduleCode);
    }

    public static NonScheduledTask makeNonScheduledTask(Description description,
                                                 ModuleCode moduleCode) {
        return new NonScheduledTask(description, moduleCode);
    }

    public String getTimeString() {
        return "";
    }

    @Override
    public Optional<LocalTime> getComparableTime() {
        return Optional.empty();
    }
}
