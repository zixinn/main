package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;

/**
 * Lists all tasks in Mod Manager to the user.
 */
public class TaskForOneModuleCommand extends TaskCommand {

    public static final String MESSAGE_SUCCESS = "All tasks for the module %s are listed. "
            + "Returns to the full task list using: task list.";
    public static final String MESSAGE_USAGE = COMMAND_GROUP_TASK + " " + COMMAND_WORD_MODULE
            + ": Lists all the tasks for the module in Mod Manager. "
            + "Parameters: "
            + PREFIX_MODULE_CODE + " MOD_CODE "
            + "Example: " + COMMAND_GROUP_TASK + " " + COMMAND_WORD_MODULE + " "
            + PREFIX_MODULE_CODE + " CS3230 ";
    public static final String MESSAGE_MODULE_DOES_NOT_EXIST = "The module %1$s does not exist in Mod Manager.";

    private final String moduleCode;

    public TaskForOneModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws ParseException, CommandException {
        requireNonNull(model);

        if (!model.hasModuleCode(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        model.updateFilteredTaskList(task -> task.getModuleCode().equals(moduleCode));
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                moduleCode), CommandType.TASK);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskForOneModuleCommand // instanceof handles nulls
                && this.moduleCode.equals(((TaskForOneModuleCommand) other).moduleCode)); // state check
    }
}
