package seedu.address.logic.commands.calendar;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class CalFindCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_calFind_success() throws CommandException {
        Model tempModel = new ModelManager();
        CommandResult expectedCommandResult = new CalFindCommand().execute(tempModel);
        assertCommandSuccess(new CalFindCommand(), model, expectedCommandResult, expectedModel);
    }

}
