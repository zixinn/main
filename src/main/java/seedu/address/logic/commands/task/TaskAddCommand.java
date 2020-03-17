package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a module to Mod Manager.
 */
public class TaskAddCommand extends TaskCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_TASK + " " + COMMAND_WORD_ADD
            + ": Adds a task to Mod Manager. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + " TASK_DESCRIPTION "
            + "[" + PREFIX_BY + " DD/MM/YYYY] "
            + "[" + PREFIX_AT + " hh/mm] \n"
            + "Example: " + COMMAND_GROUP_TASK + " " + COMMAND_WORD_ADD + " "
            + PREFIX_DESCRIPTION + " CS3230 Programming Assignment 2 "
            + "[" + PREFIX_BY + " 01/04/2020] "
            + "[" + PREFIX_AT + " 23/59] \n";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in Mod Manager. "
            + "Please choose another name, or another time slot. Thanks!";
    public static final String MESSAGE_NOT_ADDED = "Unable to add this task. "
            + "There is no information received. Please try again!";
    private final Task toAdd;

    /**
     * Creates a TaskAddCommand to add the specified {@code Task}
     */
    public TaskAddCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandType.TASK);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskAddCommand) // instanceof handles nulls
                && toAdd.equals(((TaskAddCommand) other).toAdd);
    }
}
