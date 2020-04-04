package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;
import seedu.address.model.task.util.TaskDateTime;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    /*
    private static ScheduledTask SCHEDULED_ASSIGNMENT_TASK;
    private static ScheduledTask SCHEDULED_HOMEWORK_TASK;
    private static NonScheduledTask NON_SCHEDULED_PROGRAMMING_TASK;
    private static NonScheduledTask NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK;

    static {
        try {
            // Manually added - Task's details found in {@code CommandTestUtil}
            TypicalTasks.SCHEDULED_ASSIGNMENT_TASK = new ScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2103T)
                    .withDescription(VALID_TASK_DESCRIPTION_ASSIGNMENT)
                    .withTaskDateTime("01/04/2020")
                    .withTaskId(999)
                    .withIsDone(false)
                    .build();
            TypicalTasks.SCHEDULED_HOMEWORK_TASK = new ScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2101)
                    .withDescription(VALID_TASK_DESCRIPTION_HOMEWORK)
                    .withTaskDateTime("25/06/2020")
                    .withTaskId(998)
                    .withIsDone(false)
                    .build();
            TypicalTasks.NON_SCHEDULED_PROGRAMMING_TASK = new NonScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2103T)
                    .withDescription(VALID_TASK_DESCRIPTION_PROGRAMMING)
                    .withTaskId(997)
                    .withIsDone(false)
                    .build();
            TypicalTasks.NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK = new NonScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2101)
                    .withDescription(VALID_TASK_DESCRIPTION_PROGRAMMING
                            + VALID_TASK_DESCRIPTION_ASSIGNMENT)
                    .withTaskId(996)
                    .withIsDone(false)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
     */

    public static final Task SCHEDULED_TASK_1 = new TaskBuilder().withDescription("Programming Assignment 2")
            .withModuleCode("CS4223").withTaskDateTime(new TaskDateTime("18/03/2020")).withTaskNum(111).build();
    public static final Task SCHEDULED_TASK_2 = new TaskBuilder().withDescription("Programming Assignment 3")
            .withModuleCode("CS4223").withTaskDateTime(new TaskDateTime("21/03/2020", "19:30"))
            .withTaskNum(999).build();
    public static final Task NON_SCHEDULED_TASK_1 = new TaskBuilder().withDescription("OP2 Presentation")
            .withModuleCode("CS2101").withTaskDateTime(null).withTaskNum(314).build();
    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<Task>(Arrays.asList(SCHEDULED_TASK_1, SCHEDULED_TASK_2, NON_SCHEDULED_TASK_1));
    }
}

