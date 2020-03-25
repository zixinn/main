package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.HashMap;
import java.util.stream.Stream;

import seedu.address.logic.commands.task.TaskSearchCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskSearchPredicate;


/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskSearchCommandParser implements Parser<TaskSearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns a TaskAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskSearchCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_DAY, PREFIX_MONTH, PREFIX_YEAR);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskSearchCommand.MESSAGE_USAGE));
        }

        HashMap<String, Integer> keywords = new HashMap<String, Integer>();

        if (arePrefixesPresent(argMultimap, PREFIX_DAY)) {
            int day;
            try {
                day = Integer.parseInt(argMultimap.getValue(PREFIX_DAY));
            } catch (NumberFormatException error) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TaskSearchCommand.MESSAGE_INVALID_DAY_MONTH_YEAR));
            }
            keywords.put("day", day);
        }

        if (arePrefixesPresent(argMultimap, PREFIX_MONTH)) {
            int month;
            try {
                month = Integer.parseInt(argMultimap.getValue(PREFIX_MONTH));
            } catch (NumberFormatException error) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TaskSearchCommand.MESSAGE_INVALID_DAY_MONTH_YEAR));
            }
            keywords.put("month", month);
        }

        if (arePrefixesPresent(argMultimap, PREFIX_YEAR)) {
            int year;
            try {
                year = Integer.parseInt(argMultimap.getValue(PREFIX_YEAR));
            } catch (NumberFormatException error) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TaskSearchCommand.MESSAGE_INVALID_DAY_MONTH_YEAR));
            }
            keywords.put("year", year);
        }
        return new TaskSearchCommand(new TaskSearchPredicate(keywords));
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix) != null);
    }
}
