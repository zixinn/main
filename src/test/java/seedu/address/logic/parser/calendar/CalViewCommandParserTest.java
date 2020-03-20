package seedu.address.logic.parser.calendar;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.calendar.CalViewCommand;
import seedu.address.model.calendar.Calendar;

public class CalViewCommandParserTest {
    private CalViewCommandParser parser = new CalViewCommandParser();

    @Test
    public void parse_validField_success() {
        assertParseSuccess(parser, " /week this", new CalViewCommand(Calendar.getNowCalendar()));
        assertParseSuccess(parser, " /week next", new CalViewCommand(Calendar.getNextWeekCalendar()));
    }

}
