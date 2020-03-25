package seedu.address.model.task;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.util.Description;

class TaskSearchPredicateTest {
    private static HashMap<String, Integer> keywords = new HashMap<>();

    @BeforeEach
    void setUp() {
        keywords.put("day", 1);
        keywords.put("month", 4);
        keywords.put("year", 2020);
    }

    @Test
    void test1() {
        TaskSearchPredicate predicate = new TaskSearchPredicate(keywords);
        Task task = new ScheduledTask(new Description("1"), new TaskDateTime("01/04/2020"), new ModuleCode("CS2103T"));
        System.out.println(predicate.test(task));
        System.out.println(task.getTaskDateTime());
    }
}
