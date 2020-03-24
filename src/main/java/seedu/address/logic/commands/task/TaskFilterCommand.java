package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.model.task.TaskFilterPredicate;

/**
 * Finds and lists all tasks in Mod Manager whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class TaskFilterCommand extends TaskCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_TASK + " " + COMMAND_WORD_FILTER
            + ": Finds all tasks that occur on your specified date, month, or year "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_DAY + "] dd "
            + "[" + PREFIX_MONTH + "] MM "
            + "[" + PREFIX_YEAR + "] yy \n"
            + "Example: " + COMMAND_GROUP_TASK + " " + COMMAND_WORD_FILTER + " "
            + PREFIX_MONTH + " 5 "
            + PREFIX_YEAR + " 2020\n";

    private final TaskFilterPredicate predicate;

    public TaskFilterCommand(TaskFilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        System.out.println(model.getFilteredTaskList());
        System.out.println(model.getFilteredTaskList().getClass());
        model.updateFilteredTaskList(predicate);
        System.out.println("reached");
        System.out.println(model.getFilteredTaskList());
        return new CommandResult(String.format(Messages.MESSAGE_TASKS_FILTERED_OVERVIEW,
                model.getFilteredTaskList().size()), CommandType.TASK);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskFilterCommand // instanceof handles nulls
                && predicate.equals(((TaskFilterCommand) other).predicate)); // state check
    }
}
