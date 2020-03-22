package seedu.address.logic.commands.task;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Represents a Task command with hidden internal logic and the ability to be executed.
 */
public abstract class TaskCommand extends Command {

    public static final String ADD_FORMAT = String.format("%s %s %s MOD_CODE %s DESCRIPTION %s DATE %s TIME",
            COMMAND_GROUP_TASK, COMMAND_WORD_ADD,
            PREFIX_MODULE_CODE, PREFIX_DESCRIPTION, PREFIX_ON, PREFIX_AT);
    public static final String EDIT_FORMAT = String.format("%s %s %s MOD_CODE %s DESCRIPTION %s DATE %s TIME",
            COMMAND_GROUP_TASK, COMMAND_WORD_EDIT,
            PREFIX_MODULE_CODE, PREFIX_DESCRIPTION, PREFIX_ON, PREFIX_AT);
    public static final String DELETE_FORMAT = String.format("%s %s %s DESCRIPTION",
            COMMAND_GROUP_TASK, COMMAND_WORD_DELETE,
            PREFIX_DESCRIPTION);

    public static final List<String> ALL_COMMAND_FORMATS = List.of(ADD_FORMAT, EDIT_FORMAT, DELETE_FORMAT);

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
