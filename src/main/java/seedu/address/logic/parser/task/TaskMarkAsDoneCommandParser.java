package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.task.TaskAddCommand;
import seedu.address.logic.commands.task.TaskMarkAsDoneCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.util.Description;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskMarkAsDoneCommandParser implements Parser<TaskMarkAsDoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns a TaskAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskMarkAsDoneCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_MODULE_CODE, PREFIX_TASK_ID);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskMarkAsDoneCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_ID)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskMarkAsDoneCommand.MESSAGE_TASK_ID_NOT_EXISTENT));
        }

        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE));
        int taskID = ParserUtil.parseTaskID(argMultimap.getValue(PREFIX_TASK_ID));

        return new TaskMarkAsDoneCommand(moduleCode, taskID);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix) != null);
    }
}
