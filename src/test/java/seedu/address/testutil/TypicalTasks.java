package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_PROGRAMMING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.NonScheduledTask;
import seedu.address.model.task.ScheduledTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.util.TaskDateTime;

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

//    Task.makeScheduledTask(new Description("Programming Assignment 2"), new TaskDateTime("18/03/2020")
//                        new ModuleCode("CS3233"), 111);
//    Task.makeScheduledTask(new Description("Programming Assignment 3"), new TaskDateTime("21/03/2020"),
//                        new ModuleCode("CS4223"), 999);
//    Task.makeNonScheduledTask(new Description("OP2 Presentation"), new ModuleCode("CS2103T"), 314);

    public static final Task SCHEDULED_TASK_1 = new TaskBuilder().withDescription("Programming Assignment 2")
            .withModuleCode("CS3233").withTaskDateTime(new TaskDateTime("18/03/2020")).withTaskNum(111).build();
    public static final Task SCHEDULED_TASK_2 = new TaskBuilder().withDescription("Programming Assignment 3")
            .withModuleCode("CS4223").withTaskDateTime(new TaskDateTime("21/03/2020", "19:30")).withTaskNum(999).build();
    public static final Task NON_SCHEDULED_TASK_1 = new TaskBuilder().withDescription("OP2 Presentation")
            .withModuleCode("CS2101").withTaskDateTime(null).withTaskNum(314).build();
    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<Task>(Arrays.asList(SCHEDULED_TASK_1, SCHEDULED_TASK_2, NON_SCHEDULED_TASK_1));
    }
}

