package seedu.address.logic.commands.calendar;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.calendar.Calendar;

public class CalViewCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void constructor_nullWeek_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalViewCommand(null));
    }

    @Test
    public void execute_calView_success() {
        CommandResult expectedCommandResult = new CommandResult(
                CalViewCommand.MESSAGE_SUCCESS, CommandType.CALENDAR);
        assertCommandSuccess(new CalViewCommand(
                Calendar.getNowCalendar()), model, expectedCommandResult, expectedModel);
    }
}
