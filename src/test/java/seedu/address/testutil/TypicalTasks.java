package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;

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

    private static ScheduledTask ScheduledTaskA;
    private static ScheduledTask ScheduledTaskB;
    private static ScheduledTask NonScheduledTaskC;
    private static ScheduledTask NonScheduledTaskD;

    static {
        try {
            // Manually added - Task's details found in {@code CommandTestUtil}
            final ScheduledTask ScheduledTaskA = new ScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2103T)
                    .withDescription(VALID_DESCRIPTION_CS2103T)
                    .withTaskDateTime("01/04/2020 23:59")
                    .withTaskID(999)
                    .withIsDone(false)
                    .build();
            final ScheduledTask ScheduledTaskB = new ScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2101)
                    .withDescription(VALID_DESCRIPTION_CS2101)
                    .withTaskDateTime("25/06/2020 00:01")
                    .withTaskID(998)
                    .withIsDone(false)
                    .build();
            final NonScheduledTask NonScheduledTaskC = new NonScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2103T)
                    .withDescription(VALID_DESCRIPTION_CS2103T)
                    .withTaskID(997)
                    .withIsDone(false)
                    .build();
            final NonScheduledTask NonScheduledTaskD = new NonScheduledTaskBuilder()
                    .withModuleCode(VALID_MODULE_CODE_CS2101)
                    .withDescription(VALID_DESCRIPTION_CS2101)
                    .withTaskID(996)
                    .withIsDone(false)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<Task>(Arrays.asList(ScheduledTaskA, ScheduledTaskB, NonScheduledTaskC, NonScheduledTaskD));
    }
}
