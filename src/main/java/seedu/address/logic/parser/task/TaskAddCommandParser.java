package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.stream.Stream;

import seedu.address.logic.commands.task.TaskAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDateTime;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskAddCommandParser implements Parser<TaskAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns a TaskAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_MODULE_CODE, PREFIX_TASK, PREFIX_BY, PREFIX_AT);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK) && !arePrefixesPresent(argMultimap, PREFIX_BY)
                && !arePrefixesPresent(argMultimap, PREFIX_AT)) {
            throw new ParseException(TaskAddCommand.MESSAGE_NOT_ADDED);
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_TASK));

        if (!arePrefixesPresent(argMultimap, PREFIX_BY)) { // date not supplied
            return new TaskAddCommand(new Task(description));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_AT)) { // time not supplied, only date
            return new TaskAddCommand((new Task(description, new TaskDateTime(argMultimap.getValue(PREFIX_BY)))));
        }
        // time and date supplied
        return new TaskAddCommand((new Task(description,
                new TaskDateTime(argMultimap.getValue(PREFIX_BY),
                        argMultimap.getValue(PREFIX_AT)))));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix) != null);
    }
}
