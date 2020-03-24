package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.HashMap;
import java.util.stream.Stream;

import seedu.address.logic.commands.task.TaskFilterCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskFilterPredicate;


/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskFilterCommandParser implements Parser<TaskFilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns a TaskAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_DAY, PREFIX_MONTH, PREFIX_YEAR);

        //if (!argMultimap.getPreamble().isEmpty()) {
            //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
        //}

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY, PREFIX_MONTH, PREFIX_YEAR)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskFilterCommand.MESSAGE_USAGE));
        }

        HashMap<String, Integer> keywords = new HashMap();

        if (arePrefixesPresent(argMultimap, PREFIX_DAY)) {
            keywords.put("day", Integer.parseInt(argMultimap.getValue(PREFIX_DAY)));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_MONTH)) {
            keywords.put("month", Integer.parseInt(argMultimap.getValue(PREFIX_MONTH)));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_YEAR)) {
            keywords.put("year", Integer.parseInt(argMultimap.getValue(PREFIX_YEAR)));
        }
        System.out.println("dasdsadsa");
        System.out.println(keywords);

        System.out.println(new TaskFilterPredicate(keywords));
        return new TaskFilterCommand(new TaskFilterPredicate(keywords));

    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix) != null);
    }
}
