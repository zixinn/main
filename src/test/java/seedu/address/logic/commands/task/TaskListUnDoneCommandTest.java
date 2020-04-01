package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_UNDONE_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIND_WORDS_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.task.TaskListUnDoneCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK;
import static seedu.address.testutil.TypicalTasks.NON_SCHEDULED_PROGRAMMING_TASK;
import static seedu.address.testutil.TypicalTasks.SCHEDULED_ASSIGNMENT_TASK;
import static seedu.address.testutil.TypicalTasks.SCHEDULED_HOMEWORK_TASK;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.testutil.TypicalTasks;
/*
class TaskListUnDoneCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
    @Test
    public void execute_everyTaskIsNotDone_allTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_UNDONE_OVERVIEW, 4);
        TaskListUnDoneCommand command = new TaskListUnDoneCommand();
        expectedModel.updateFilteredTaskList(task -> !task.isTaskDone());
        //assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(
                SCHEDULED_ASSIGNMENT_TASK,
                SCHEDULED_HOMEWORK_TASK,
                NON_SCHEDULED_PROGRAMMING_TASK,
                NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_markedAllTasksAsDone_noTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_UNDONE_OVERVIEW, 0);
        TaskListUnDoneCommand command = new TaskListUnDoneCommand();

        for (Task task: model.getFilteredTaskList()) {
            task.markAsDone(); // mark all tasks as done
        }
        expectedModel.updateFilteredTaskList(task -> !task.isTaskDone());
        //assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_markedOneTaskAsDone_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_UNDONE_OVERVIEW, 3);
        TaskListUnDoneCommand command = new TaskListUnDoneCommand();

        assert (expectedModel.getFilteredTaskList().size() > 0);
        expectedModel.getFilteredTaskList().get(0).markAsDone(); // mark first task as done
        expectedModel.updateFilteredTaskList(task -> !task.isTaskDone());

        //assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(
                SCHEDULED_HOMEWORK_TASK,
                NON_SCHEDULED_PROGRAMMING_TASK,
                NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK), expectedModel.getFilteredTaskList());
    }
}
*/
