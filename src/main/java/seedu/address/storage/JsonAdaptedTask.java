package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.util.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDateTime;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;
    private final String taskTime;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description,
                           @JsonProperty("taskTime") String taskTime) {
        this.description = description;
        this.taskTime = taskTime;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        description = source.getDescription();
        this.taskTime = source.getTimeOutput();
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!TaskDateTime.isValidTaskTime(taskTime)) {
            throw new IllegalValueException(TaskDateTime.MESSAGE_CONSTRAINTS);
        }

        final TaskDateTime modelTaskTime;
        if (taskTime.length() <= 10) {
            modelTaskTime = new TaskDateTime(taskTime);
        } else {
            modelTaskTime = new TaskDateTime(taskTime.split(" ")[0], taskTime.split(" ")[1]);
        }
        if (description != null && !Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);
        System.out.println("time received " + modelTaskTime);
        return new Task(modelDescription, modelTaskTime);
    }
}
