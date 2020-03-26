package seedu.address.logic.parser.lesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.lesson.LessonAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonType;
import seedu.address.model.lesson.exceptions.InvalidTimeRangeException;
import seedu.address.model.module.ModuleCode;

/**
 * Adds a lesson to Mod Manager.
 */
public class LessonAddCommandParser implements Parser<LessonAddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LessonAddCommand
     * and returns a LessonAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_MODULE_CODE, PREFIX_AT, PREFIX_VENUE, PREFIX_TYPE, PREFIX_NEXT);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE)
                || !arePrefixesPresent(argMultimap, PREFIX_TYPE)
                || !arePrefixesPresent(argMultimap, PREFIX_AT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonAddCommand.MESSAGE_USAGE));
        }

        String venue = null;
        if (arePrefixesPresent(argMultimap, PREFIX_VENUE)) {
            venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE));
        }

        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE));
        LessonType lessonType = ParserUtil.parseLessonType(argMultimap.getValue(PREFIX_TYPE));

        String trimmed = argMultimap.getValue(PREFIX_AT).trim();
        String[] splitted = trimmed.split(" ");
        if (splitted.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonAddCommand.MESSAGE_USAGE));
        }
        DayOfWeek day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_AT));

        LocalTime startTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_AT));
        LocalTime endTime = ParserUtil.parseEndTime(argMultimap.getValue(PREFIX_AT));


        Lesson lesson;
        try {
            lesson = new Lesson(moduleCode, lessonType, day, startTime, endTime, venue);
        } catch (InvalidTimeRangeException e) {
            throw new ParseException("Start time should be earlier than end time", e);
        }

        return new LessonAddCommand(lesson);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix) != null);
    }
}
