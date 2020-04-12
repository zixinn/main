package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BROKEN_FIND_WORDS_AS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BROKEN_FIND_WORDS_HOME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIND_WORDS_PROGRAMMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS3230;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.ES2660;
import static seedu.address.testutil.TypicalTasks.nonScheduledProgrammingAssignmentTask;
import static seedu.address.testutil.TypicalTasks.scheduledAssignmentTask;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class TaskContainsInModulePredicateTest {

    @Test
    public void equals() {
        String firstModuleCode = VALID_MODULE_CODE_CS2103T;
        String secondModuleCode = VALID_MODULE_CODE_CS2101;

        TaskContainsInModulePredicate firstPredicate = new TaskContainsInModulePredicate(firstModuleCode);
        TaskContainsInModulePredicate secondPredicate = new TaskContainsInModulePredicate(secondModuleCode);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same module code -> returns true
        TaskContainsInModulePredicate firstPredicateCopy = new TaskContainsInModulePredicate(firstModuleCode);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // predicates with different module codes -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskModuleCodeMatches_returnsTrue() {
        String moduleCode = VALID_MODULE_CODE_CS2103T;
        TaskContainsInModulePredicate predicate = new TaskContainsInModulePredicate(moduleCode);

        // task's module code matches -> returns true
        assertTrue(predicate.test(scheduledAssignmentTask));
    }

    @Test
    public void test_taskModuleCodeDoesNotMatch_returnsFalse() {
        String moduleCode = VALID_MODULE_CODE_CS3230;
        TaskContainsInModulePredicate predicate = new TaskContainsInModulePredicate(moduleCode);

        // task's module code does not match (CS2101 instead) -> returns false
        assertFalse(predicate.test(nonScheduledProgrammingAssignmentTask));
    }
}