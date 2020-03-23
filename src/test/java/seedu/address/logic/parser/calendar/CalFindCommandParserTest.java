package seedu.address.logic.parser.calendar;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.calendar.CalFindCommand;

public class CalFindCommandParserTest {
    private CalFindCommandParser parser = new CalFindCommandParser();

    @Test
    public void parse_validArgs_returnsCalFindCommand() {
        assertParseSuccess(parser, "empty", new CalFindCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalFindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "hello",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalFindCommand.MESSAGE_USAGE));
    }
}
