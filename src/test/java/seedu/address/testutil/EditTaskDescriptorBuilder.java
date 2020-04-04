package seedu.address.testutil;

import seedu.address.logic.commands.task.TaskEditCommand;
import seedu.address.model.task.Task;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.util.Description;

public class EditTaskDescriptorBuilder {
    private TaskEditCommand.EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new TaskEditCommand.EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(TaskEditCommand.EditTaskDescriptor descriptor) {
        this.descriptor = new TaskEditCommand.EditTaskDescriptor(descriptor);
    }

    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new TaskEditCommand.EditTaskDescriptor(task);
    }

    public EditTaskDescriptorBuilder withDescription(String desc) {
        descriptor.setDescription(new Description(desc));
        return this;
    }

    public EditTaskDescriptorBuilder withTaskDateTime(TaskDateTime tdt) {
        descriptor.setTaskDateTime(tdt);
        return this;
    }

    public TaskEditCommand.EditTaskDescriptor build() {
        return descriptor;

    }
}
