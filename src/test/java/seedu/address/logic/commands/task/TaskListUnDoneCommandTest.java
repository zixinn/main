package seedu.address.logic.commands.task;

/*
class TaskListUnDoneCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModManager(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalModManager(), new UserPrefs());
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
