package seedu.address.logic.commands.calendar;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResultUi;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class CalViewCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void constructor_nullWeek_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalViewCommand(null));
    }

    @Test
    public void execute_calView_success() {
        CommandResult expectedCommandResult = new CommandResultUi(
                CalViewCommand.MESSAGE_SUCCESS, CommandType.CALENDAR, "this");
        assertCommandSuccess(new CalViewCommand("this"), model, expectedCommandResult, expectedModel);
    }
}
