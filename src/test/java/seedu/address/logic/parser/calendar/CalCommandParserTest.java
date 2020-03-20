package seedu.address.logic.parser.calendar;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_CALENDAR_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CalCommandParserTest {
    private CalCommandParser parser = new CalCommandParser();

    @Test
    public void parse_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_UNKNOWN_CALENDAR_COMMAND,
                HelpCommand.MESSAGE_USAGE), () -> parser.parse(""));
    }

    @Test
    public void parse_unknownCalendarCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_UNKNOWN_CALENDAR_COMMAND,
                HelpCommand.MESSAGE_USAGE), () -> parser.parse("cal unknownCommand"));
    }
}
