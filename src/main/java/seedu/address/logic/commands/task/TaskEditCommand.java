package seedu.address.logic.commands.task;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.util.Description;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;

public class TaskEditCommand extends Command {
    public static final String MESSAGE_USAGE = COMMAND_GROUP_TASK + " " + COMMAND_WORD_EDIT
            + ": Edits the details of the task identified "
            + "by the unique ID of the task found in the both the general tasks list and module specific list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: MODULE_CODE ID_NUMBER "
            + "[ " + PREFIX_DESCRIPTION + " DESCRIPTION] "
            + "[ " + PREFIX_ON + " DATE] "
            + "[ " + PREFIX_AT + " TIME]\n"
            + "Example: " + COMMAND_GROUP_TASK + " " + COMMAND_WORD_EDIT + " CS2103T 848 "
            + PREFIX_DESCRIPTION + "UG submission "
            + PREFIX_ON + " 12/04/2020"
            + PREFIX_AT + " 23:59";
    
    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in Mod Manager.";
    public static final String MESSAGE_ALL_OPTIONAL_FIELDS_DELETED =
            "At least one of the fields must not be empty.";

    private final ModuleCode moduleCode;
    private final int taskID;
    private final EditTaskDescriptor editTaskDescriptor;

    public TaskEditCommand(ModuleCode moduleCode, int taskID, EditTaskDescriptor editTaskDescriptor) {
        this.moduleCode = moduleCode;
        this.taskID = taskID;
        this.editTaskDescriptor = editTaskDescriptor;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);


        return null;
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
