package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.AN_INT_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DAY_26;
import static seedu.address.logic.commands.CommandTestUtil.TASK_YEAR_2020;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandType.TASK;
import static seedu.address.logic.parser.task.TaskSearchCommandParser.YEAR_STRING;
import static seedu.address.testutil.TypicalModManager.getTypicalModManager;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.ES2660;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskContainsInModulePredicate;
import seedu.address.model.task.TaskSearchPredicate;

class TaskForOneModuleCommandTest {
    private Model model = new ModelManager(getTypicalModManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalModManager(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsInModulePredicate firstPredicate =
                new TaskContainsInModulePredicate(CS2103T.toString());
        TaskContainsInModulePredicate secondPredicate =
                new TaskContainsInModulePredicate(CS2103T.toString());
        TaskContainsInModulePredicate thirdDifferentPredicate =
                new TaskContainsInModulePredicate(ES2660.toString());

        TaskForOneModuleCommand taskInModuleFirstCommand = new TaskForOneModuleCommand(firstPredicate);
        TaskForOneModuleCommand taskInModuleSecondCommand = new TaskForOneModuleCommand(secondPredicate);
        TaskForOneModuleCommand taskInModuleThirdCommandDifferentMod =
                new TaskForOneModuleCommand(thirdDifferentPredicate);

        // same object -> returns true
        assertTrue(taskInModuleFirstCommand.equals(taskInModuleFirstCommand));

        // same values -> returns true
        TaskForOneModuleCommand taskInModuleFirstCommandCopy = new TaskForOneModuleCommand(firstPredicate);
        assertTrue(taskInModuleFirstCommand.equals(taskInModuleFirstCommandCopy));

        // different types -> returns false
        assertFalse(taskInModuleFirstCommand.equals(AN_INT_VALUE));

        // null -> returns false
        assertFalse(taskInModuleFirstCommand.equals(null));

        // same module code -> returns true
        assertTrue(taskInModuleFirstCommand.equals(taskInModuleSecondCommand));

        // different module codes -> returns false
        assertFalse(taskInModuleFirstCommand.equals(taskInModuleThirdCommandDifferentMod));
    }

    @Test
    public void execute_moduleNotAvailableInModManager_failure() {
        System.out.println(model.getFilteredModuleList());
        System.out.println("yay");
    }

    @Test
    public void execute_moduleAvailableInModManager_success() {
        String moduleCode = CS2101.toString();
        TaskContainsInModulePredicate predicate =
                new TaskContainsInModulePredicate(moduleCode);
        TaskForOneModuleCommand taskInModuleFirstCommand = new TaskForOneModuleCommand(predicate);

        System.out.println(model.getFilteredModuleList());
        System.out.println("vroom");
        expectedModel.updateFilteredTaskList(predicate);
        String expectedMessage = String.format(TaskForOneModuleCommand.MESSAGE_SUCCESS, moduleCode);
        assertCommandSuccess(taskInModuleFirstCommand, model, expectedMessage,
                CommandType.TASK, expectedModel);
    }
}
