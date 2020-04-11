/*
package seedu.address.logic.commands.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_UNDONE_OVERVIEW;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.task.Task;

class TaskListUnDoneCommandTest {
    private Model model;
    private Model expectedModel;

    @Test
    public void execute_everyTaskIsNotDone_allTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_UNDONE_OVERVIEW, 4);
        TaskListUnDoneCommand command = new TaskListUnDoneCommand();
        expectedModel.updateFilteredTaskList(task -> !task.isTaskDone());
        //assertCommandSuccess(command, model, expectedMessage, CommandType.TASK, expectedModel);
        assertEquals(Arrays.asList(
                scheduledAssignmentTask,
                scheduledHomeworkTask,
                nonScheduledProgrammingTask,
                nonScheduledProgrammingAssignmentTask), expectedModel.getFilteredTaskList());
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
                scheduledHomeworkTask,
                nonScheduledProgrammingTask,
                nonScheduledProgrammingAssignmentTask), expectedModel.getFilteredTaskList());
    }
}
*/
