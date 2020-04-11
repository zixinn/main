package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.task.TaskSearchCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskSearchPredicate;

/**
 * Parses input arguments and creates a new TaskSearchCommand object
 */
public class TaskSearchCommandParser implements Parser<TaskSearchCommand> {

    private static final int MINIMUM_YEAR = 1;
    private static final int MAXIMUM_YEAR = 9999;
    private static final int MINIMUM_MONTH = 1;
    private static final int MAXIMUM_MONTH = 12;
    private static final int MINIMUM_DATE = 1;
    private static final int MAXIMUM_DATE = 31;

    private static final int THIRTY_MAXIMUM_DATE = 30;
    private static final int TWENTY_NINE_MAXIMUM_DATE = 29;
    private static final int TWENTY_EIGHT_MAXIMUM_DATE = 28;

    private static final int FEBRUARY = 2;

    private static final List<Integer> THIRTY_DAY_MONTHS = new ArrayList<Integer>(Arrays.asList(4, 6, 9, 11));
    /**
     * Parses the given {@code String} of arguments in the context of the TaskSearchCommand
     * and returns a TaskSearchCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskSearchCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_DAY, PREFIX_MONTH, PREFIX_YEAR);

        HashMap<String, Integer> keywords = new HashMap<String, Integer>();

        if (argMultimap.numOfValuesPresent(PREFIX_DAY) > 1) {
            throw new ParseException(String.format(MESSAGE_TOO_MANY_ARGUMENTS, "one", PREFIX_DAY));
        }
        if (argMultimap.numOfValuesPresent(PREFIX_MONTH) > 1) {
            throw new ParseException(String.format(MESSAGE_TOO_MANY_ARGUMENTS, "one", PREFIX_MONTH));
        }
        if (argMultimap.numOfValuesPresent(PREFIX_YEAR) > 1) {
            throw new ParseException(String.format(MESSAGE_TOO_MANY_ARGUMENTS, "one", PREFIX_YEAR));
        }

        // check for invalid date, month, and/or year, such as strings and characters

        if (arePrefixesPresent(argMultimap, PREFIX_DAY)) {
            int day;
            try {
                day = Integer.parseInt(argMultimap.getValue(PREFIX_DAY));
            } catch (NumberFormatException error) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TaskSearchCommand.MESSAGE_INVALID_DAY_MONTH_YEAR));
            }
            keywords.put("date", day);
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

        // check for out of bounds date, month, and/or year

        boolean isFullDateMonthYear = keywords.size() == 3;
        boolean twoOutOfThreeParametersProvided = keywords.size() == 2;
        boolean oneParameterProvided = keywords.size() == 1;

        if (isFullDateMonthYear) {
            if (!isValidDate(keywords.get("date"), keywords.get("month"), keywords.get("year"))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
            }
        } else if (twoOutOfThreeParametersProvided) { // dd/MM, MM/yy, or dd/yy
            if (keywords.containsKey("year")) { // dd/YY or MM/yy -> can just check independently
                if (isOutOfBoundsYear(keywords.get("year"))) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                }

                if (keywords.containsKey("date")) { // dd/YY
                    if (isOutOfBoundsDate(keywords.get("date"))) {
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                    }
                } else { // mm/YY
                    if (isOutOfBoundsMonth(keywords.get("month"))) {
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                    }
                }
            } else { // dd/MM, painful combination to check

                if (isOutOfBoundsMonth(keywords.get("month"))) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                }

                if (keywords.get("month") == FEBRUARY) {
                    if (!(MINIMUM_DATE <= keywords.get("date")
                            && (keywords.get("date")) <= TWENTY_NINE_MAXIMUM_DATE)) { // not valid days in Feb
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                    }
                } else if (THIRTY_DAY_MONTHS.contains(keywords.get("month"))) { // thirty day months
                    if (!(MINIMUM_DATE <= keywords.get("date")
                            && (keywords.get("date") <= THIRTY_MAXIMUM_DATE))) { // not valid days in Feb
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                    }
                } else { // thirty one day months
                    if (isOutOfBoundsDate(keywords.get("date"))) {
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                    }
                }
            }
        } else if (oneParameterProvided) {
            if (keywords.containsKey("date")) {
                if (isOutOfBoundsDate(keywords.get("date"))) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                }
            } else if (keywords.containsKey("month")) {
                if (isOutOfBoundsMonth(keywords.get("month"))) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                }
            } else if (keywords.containsKey("year")) {
                if (isOutOfBoundsYear(keywords.get("year"))) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TaskSearchCommand.MESSAGE_OUT_OF_BOUNDS_VALUES));
                }
            }
        }

        System.out.println(keywords);
        return new TaskSearchCommand(new TaskSearchPredicate(keywords));
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix) != null);
    }

    private boolean isLeapYear(int year) {
        return ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0));
    }

    /**
     * Returns true if the date {@code date}/{@code month}/{@code year} is valid
     */
    private boolean isValidDate(int date, int month, int year) {
        if (isOutOfBoundsYear(year)) {
            return false;
        }

        if (isOutOfBoundsMonth(month)) {
            return false;
        }

        if (isOutOfBoundsDate(date)) {
            return false;
        }

        if (THIRTY_DAY_MONTHS.contains(month) && date > THIRTY_MAXIMUM_DATE) {
            return false;
        }

        if (isLeapYear(year)) {
            if (month == FEBRUARY && date > TWENTY_NINE_MAXIMUM_DATE) {
                return false;
            }
        } else {
            if (month == FEBRUARY && date > TWENTY_EIGHT_MAXIMUM_DATE) {
                return false;
            }
        }

        return true;

    }

    private boolean isOutOfBoundsYear(int year) {
        return !(MINIMUM_YEAR <= year && year <= MAXIMUM_YEAR);
    }

    private boolean isOutOfBoundsMonth(int month) {
        return !(MINIMUM_MONTH <= month && month <= MAXIMUM_MONTH);
    }

    private boolean isOutOfBoundsDate(int date) {
        return !(MINIMUM_DATE <= date && date <= MAXIMUM_DATE);
    }
}
