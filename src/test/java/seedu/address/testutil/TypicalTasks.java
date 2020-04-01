package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_PROGRAMMING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.task.NonScheduledTask;
import seedu.address.model.task.ScheduledTask;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalTasks {

    public static ScheduledTask SCHEDULED_ASSIGNMENT_TASK;
    public static ScheduledTask SCHEDULED_HOMEWORK_TASK;
    public static NonScheduledTask NON_SCHEDULED_PROGRAMMING_TASK;
    public static NonScheduledTask NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK;

    static {
        try {
            // Manually added - Task's details found in {@code CommandTestUtil}
            TypicalTasks.SCHEDULED_ASSIGNMENT_TASK = new ScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2103T)
                    .withDescription(VALID_TASK_DESCRIPTION_ASSIGNMENT)
                    .withTaskDateTime("01/04/2020")
                    .withTaskID(999)
                    .withIsDone(false)
                    .build();
            TypicalTasks.SCHEDULED_HOMEWORK_TASK = new ScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2101)
                    .withDescription(VALID_TASK_DESCRIPTION_HOMEWORK)
                    .withTaskDateTime("25/06/2020")
                    .withTaskID(998)
                    .withIsDone(false)
                    .build();
            TypicalTasks.NON_SCHEDULED_PROGRAMMING_TASK = new NonScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2103T)
                    .withDescription(VALID_TASK_DESCRIPTION_PROGRAMMING)
                    .withTaskID(997)
                    .withIsDone(false)
                    .build();
            TypicalTasks.NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK = new NonScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2101)
                    .withDescription(VALID_TASK_DESCRIPTION_PROGRAMMING
                            + VALID_TASK_DESCRIPTION_ASSIGNMENT)
                    .withTaskID(996)
                    .withIsDone(false)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<Task>(Arrays.asList(SCHEDULED_ASSIGNMENT_TASK
                , SCHEDULED_HOMEWORK_TASK
                , NON_SCHEDULED_PROGRAMMING_TASK
                , NON_SCHEDULED_PROGRAMMING_ASSIGNMENT_TASK));
    }
}
