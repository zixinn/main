package seedu.address.testutil;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.NonScheduledTask;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.util.Description;

/**
 * A utility class to help with building non-scheduled task objects.
 */
public class NonScheduledTaskBuilder {
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final String DEFAULT_DESCRIPTION = "Programming Assignment";
    public static final boolean DEFAULT_IS_DONE = false;
    private static final int DEFAULT_TASK_ID = 999;

    private ModuleCode moduleCode;
    private Description description;
    private boolean isDone;
    private int taskID;

    public NonScheduledTaskBuilder() throws ParseException {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        description = new Description(DEFAULT_DESCRIPTION);
        isDone = DEFAULT_IS_DONE;
        taskID = DEFAULT_TASK_ID;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code ScheduledTask} that we are building.
     */
    public NonScheduledTaskBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code ScheduledTask} that we are building.
     */
    public NonScheduledTaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code ScheduledTask} that we are building.
     */
    public NonScheduledTaskBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Sets the {@code taskID} of the {@code ScheduledTask} that we are building.
     */
    public NonScheduledTaskBuilder withTaskID(int taskID) {
        this.taskID = taskID;
        return this;
    }

    public NonScheduledTask build() {
        return new NonScheduledTask(description, moduleCode, taskID, isDone);
    }
}
