package seedu.address.logic.parser.lesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.lesson.LessonFindCommand;

public class LessonFindCommandParserTest {
    private LessonFindCommandParser parser = new LessonFindCommandParser();

    @Test
    public void parse_emptyArg_throwParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        LessonFindCommand command = new LessonFindCommand(DayOfWeek.FRIDAY);
        assertParseSuccess(parser, " /at FRIDAY ", command);
    }

}
