package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.task.util.TaskIDManager;
import seedu.address.model.util.Description;

/**
 * Represents a command that edits an existing task in Mod Manager.
 */
public class TaskEditCommand extends Command {
    public static final String MESSAGE_USAGE = COMMAND_GROUP_TASK + " " + COMMAND_WORD_EDIT
            + ": Edits the details of the task identified "
            + "by the unique ID of the task found in the both the general tasks list and module specific list. "
            + "Existing values will be overwritten by the input values.\n"
            + "If you simply want to remove the DateTime field, input 'non' as parameter for " + PREFIX_AT
            + " without adding " + PREFIX_DESCRIPTION + " and " + PREFIX_ON + " parameters.\n"
            + "Parameters: MODULE_CODE ID_NUMBER "
            + "[ " + PREFIX_DESCRIPTION + " DESCRIPTION] "
            + "[ " + PREFIX_ON + " DATE/non] "
            + "[ " + PREFIX_AT + " TIME]\n"
            + "Example: " + COMMAND_GROUP_TASK + " " + COMMAND_WORD_EDIT + " CS2103T 848 "
            + PREFIX_DESCRIPTION + "UG submission "
            + PREFIX_ON + " 12/04/2020"
            + PREFIX_AT + " 23:59\n"
            + COMMAND_GROUP_TASK + " " + COMMAND_WORD_EDIT + " CS2103T 848 "
            + PREFIX_AT + " non";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in Mod Manager.";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task of module %s with ID %d does not exist in Mod Manager.";
    public static final String MESSAGE_MODULE_NOT_FOUND = "The module %s does not exist in Mod Manager.";

    private final ModuleCode moduleCode;
    private final int taskID;
    private final EditTaskDescriptor editTaskDescriptor;

    public TaskEditCommand(ModuleCode moduleCode, int taskID, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(moduleCode);
        requireNonNull(editTaskDescriptor);

        this.moduleCode = moduleCode;
        this.taskID = taskID;
        this.editTaskDescriptor = editTaskDescriptor;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);
        List<Task> current = model.getFilteredTaskList();

        if (!model.hasModuleCode(moduleCode.toString())) {
            throw new CommandException(String.format(MESSAGE_MODULE_NOT_FOUND, moduleCode.toString()));
        }

        if (!TaskIDManager.doesIdExist(moduleCode, taskID)) {
            throw new CommandException(String.format(MESSAGE_TASK_NOT_FOUND, moduleCode.toString(), taskID));
        }

        Task[] querry = (Task[]) current.stream().filter(task -> task.getTaskID() == this.taskID).toArray();
        assert querry.length == 1;

        Task taskToEdit = querry[0];
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (editTaskDescriptor.getDescription().isEmpty()
                && editTaskDescriptor.getTaskDateTime().equals(taskToEdit.getTaskDateTime())) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask),
                CommandType.TASK);
    }

    private static Task createEditedTask(Task toEdit, EditTaskDescriptor editTaskDescriptor) {
        assert toEdit != null;
        assert toEdit.getTaskID() == editTaskDescriptor.getTaskID();
        assert toEdit.getModuleCode().equals(editTaskDescriptor.getModuleCode());

        final Description updatedDescription = editTaskDescriptor.getDescription().orElse(toEdit.getDescription());
        final Optional<TaskDateTime> updatedTaskDateTime = editTaskDescriptor.getTaskDateTime();
        final ModuleCode modCode = toEdit.getModuleCode();
        final int taskId = toEdit.getTaskID();

        if (updatedTaskDateTime.isPresent()) {
            return Task.makeScheduledTask(updatedDescription, updatedTaskDateTime.get(),
                    toEdit.getModuleCode(), toEdit.getTaskID());
        } else {
            final Task[] toReturn = new Task[1];
            toEdit.getTaskDateTime()
                    .ifPresentOrElse(
                            taskDateTime -> toReturn[0] = Task.makeScheduledTask(updatedDescription, taskDateTime,
                                    modCode, taskId),
                            () -> toReturn[0] = Task.makeNonScheduledTask(updatedDescription,
                                    modCode, taskId));

            return toReturn[0];
        }
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private ModuleCode moduleCode;
        private int taskID;
        private Description description;
        private TaskDateTime taskDateTime;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {

        }

        public ModuleCode getModuleCode() {
            return this.moduleCode;
        }

        public int getTaskID() {
            return this.taskID;
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, taskDateTime);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(this.description);
        }

        public void setTaskDateTime(TaskDateTime taskDateTime) {
            this.taskDateTime = taskDateTime;
        }

        public Optional<TaskDateTime> getTaskDateTime() {
            return Optional.ofNullable(this.taskDateTime);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return this.moduleCode.equals(e.moduleCode)
                    && this.taskID == e.taskID
                    && this.description.equals(e.description)
                    && this.taskDateTime.equals(e.taskDateTime);
        }
    }
}
