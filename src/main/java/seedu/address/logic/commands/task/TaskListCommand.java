package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Lists all facilitators in Mod Manager to the user.
 */
public class TaskListCommand extends TaskCommand {

    public static final String MESSAGE_SUCCESS = "All tasks across all modules in the Mod Manager are listed.";

    @Override
    public CommandResult execute(Model model) throws ParseException {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS, CommandType.TASK);
    }
}
