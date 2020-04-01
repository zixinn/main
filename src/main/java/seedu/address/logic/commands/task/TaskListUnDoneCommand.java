package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.facilitator.FacilFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Lists all tasks in Mod Manager to the user.
 */
public class TaskListUnDoneCommand extends TaskCommand {

    public static final String MESSAGE_SUCCESS = "All undone tasks in the Mod Manager are listed.";

    @Override
    public CommandResult execute(Model model) throws ParseException {
        requireNonNull(model);
        model.updateFilteredTaskList(task -> !task.isTaskDone());
        return new CommandResult(String.format(Messages.MESSAGE_TASKS_UNDONE_OVERVIEW,
                model.getFilteredTaskList().size()), CommandType.TASK);
    }
}
