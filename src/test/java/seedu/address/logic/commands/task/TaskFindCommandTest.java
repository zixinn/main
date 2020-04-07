//@Todo: dinhnhobao
/*
package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FACILITATORS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.AN_INT_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_SPACES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BROKEN_FIND_WORDS_AS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BROKEN_FIND_WORDS_HOME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BROKEN_FIND_WORDS_ONLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BROKEN_FIND_WORDS_PROG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BROKEN_FIND_WORDS_SLEEP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIND_WORDS_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIND_WORDS_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIND_WORDS_PROGRAMMING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModManager.getTypicalModManager;
import static seedu.address.testutil.TypicalTasks.NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK;
import static seedu.address.testutil.TypicalTasks.NON_SCHEDULED_PROGRAMMING_TASK;
import static seedu.address.testutil.TypicalTasks.SCHEDULED_ASSIGNMENT_TASK;
import static seedu.address.testutil.TypicalTasks.SCHEDULED_HOMEWORK_TASK;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.task.TaskFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code TaskFindCommand}.
 */
/*
public class TaskFindCommandTest {
    private Model model = new ModelManager(getTypicalModManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalModManager(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsKeywordsPredicate firstPredicate =
                new TaskContainsKeywordsPredicate(Collections.singletonList(VALID_FIND_WORDS_PROGRAMMING));
        TaskContainsKeywordsPredicate secondPredicate =
                new TaskContainsKeywordsPredicate(Collections.singletonList(VALID_FIND_WORDS_HOMEWORK));

        TaskFindCommand findFirstCommand = new TaskFindCommand(firstPredicate);
        TaskFindCommand findSecondCommand = new TaskFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        TaskFindCommand findFirstCommandCopy = new TaskFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(AN_INT_VALUE));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different keywords to find for -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsKeywordsPredicate predicate = preparePredicate(EMPTY_SPACES);
        TaskFindCommand command = new TaskFindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_fullMatchOneKeyword_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordsPredicate predicate = preparePredicate(
                VALID_FIND_WORDS_ASSIGNMENT); // "programming"
        TaskFindCommand command = new TaskFindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(
                SCHEDULED_ASSIGNMENT_TASK,
                NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK),
                expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_partialMatchOneKeyword_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordsPredicate predicate = preparePredicate(
                VALID_BROKEN_FIND_WORDS_PROG); // finds "pROg" in "programming
        TaskFindCommand command = new TaskFindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(
                NON_SCHEDULED_PROGRAMMING_TASK,
                NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK),
                expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_fullMatchMultipleKeywords_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        TaskContainsKeywordsPredicate predicate = preparePredicate(
                VALID_FIND_WORDS_ASSIGNMENT
                + " " + VALID_FIND_WORDS_PROGRAMMING); // find words: "assignment" & "programming"
        TaskFindCommand command = new TaskFindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(
                SCHEDULED_ASSIGNMENT_TASK, // contains word "assignment"
                NON_SCHEDULED_PROGRAMMING_TASK, // contains word "programming"
                NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK),  // contains both "assignment" & "programming"
                expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_partialMatchMultipleKeywords_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 4);
        TaskContainsKeywordsPredicate predicate = preparePredicate(
                VALID_BROKEN_FIND_WORDS_AS
                        + " " + VALID_BROKEN_FIND_WORDS_PROG
                        + " " + VALID_BROKEN_FIND_WORDS_HOME);
        TaskFindCommand command = new TaskFindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(
                SCHEDULED_ASSIGNMENT_TASK, // contains word "assignment"
                SCHEDULED_HOMEWORK_TASK, // contains word "homework"
                NON_SCHEDULED_PROGRAMMING_TASK, // contains word "programming"
                NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK),  // contains both "assignment" & "programming"
                expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_fullMatchDuplicateKeywords_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordsPredicate predicate = preparePredicate(
                VALID_FIND_WORDS_ASSIGNMENT
                        + " " + VALID_FIND_WORDS_ASSIGNMENT
                        + " " + VALID_FIND_WORDS_ASSIGNMENT
                        + " " + VALID_FIND_WORDS_ASSIGNMENT);
        TaskFindCommand command = new TaskFindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(SCHEDULED_ASSIGNMENT_TASK,
                NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK),  // contains word "programming" which includes "pROg"
                expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_partialMatchDuplicateKeywords_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        TaskContainsKeywordsPredicate predicate = preparePredicate(
                VALID_BROKEN_FIND_WORDS_PROG
                        + " " + VALID_BROKEN_FIND_WORDS_PROG
                        + " " + VALID_BROKEN_FIND_WORDS_HOME);
        TaskFindCommand command = new TaskFindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(
                SCHEDULED_HOMEWORK_TASK,
                NON_SCHEDULED_PROGRAMMING_TASK, // contains word "programming" which includes "pROg"
                NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK),  // contains word "programming" which includes "pROg"
                expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_partialMatchDuplicateAndUniqueKeywords_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        TaskContainsKeywordsPredicate predicate = preparePredicate(
                VALID_BROKEN_FIND_WORDS_PROG
                        + " " + VALID_BROKEN_FIND_WORDS_PROG
                        + " " + VALID_BROKEN_FIND_WORDS_HOME
                        + " " + VALID_BROKEN_FIND_WORDS_PROG);
        TaskFindCommand command = new TaskFindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(
                SCHEDULED_HOMEWORK_TASK,
                NON_SCHEDULED_PROGRAMMING_TASK, // contains word "programming" which includes "pROg"
                NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK),  // contains word "programming" which includes "pROg"
                expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_DuplicateAndUniqueKeywords_noTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsKeywordsPredicate predicate = preparePredicate(
                VALID_BROKEN_FIND_WORDS_SLEEP
                        + " " + VALID_BROKEN_FIND_WORDS_ONLINE
                        + " " + VALID_BROKEN_FIND_WORDS_SLEEP
                        + " " + VALID_BROKEN_FIND_WORDS_SLEEP);
        TaskFindCommand command = new TaskFindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(),
                expectedModel.getFilteredTaskList());
    }
    /**
     * Parses {@code userInput} into a {@code TaskContainsKeywordsPredicate}.
     */
/*
    private TaskContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TaskContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
*/

