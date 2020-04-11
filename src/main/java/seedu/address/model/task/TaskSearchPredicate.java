package seedu.address.model.task;

import java.util.HashMap;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class TaskSearchPredicate implements Predicate<Task> {

    private final HashMap<String, Integer> keywords;

    public TaskSearchPredicate(HashMap<String, Integer> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {

        if (task.getTaskDateTime().isEmpty()) { // not a ScheduledTask
            return false;
        }

        if (keywords.containsKey("date")) {
            if (!task.getTaskDateTime().get().isDateOnThisDay(keywords.get("date"))) {
                return false;
            }
        }

        if (keywords.containsKey("month")) {
            if (!task.getTaskDateTime().get().isDateOnThisMonth(keywords.get("month"))) {
                return false;
            }
        }

        if (keywords.containsKey("year")) {
            if (!task.getTaskDateTime().get().isDateOnThisYear(keywords.get("year"))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskSearchPredicate // instanceof handles nulls
                && keywords.equals(((TaskSearchPredicate) other).keywords)); // state check
    }

}
