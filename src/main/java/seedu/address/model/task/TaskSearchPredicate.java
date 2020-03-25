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

        boolean checkDay = true;
        boolean checkMonth = true;
        boolean checkYear = true;

        if (keywords.containsKey("day")) {
            checkDay = (task.getTaskDateTime().get().isDateOnThisDay(keywords.get("day")));
        }

        if (keywords.containsKey("month")) {
            checkMonth = (task.getTaskDateTime().get().isDateOnThisMonth(keywords.get("month")));
        }

        if (keywords.containsKey("year")) {
            checkYear = (task.getTaskDateTime().get().isDateOnThisYear(keywords.get("year")));
        }

        return checkDay && checkMonth && checkYear;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskSearchPredicate // instanceof handles nulls
                && keywords.equals(((TaskSearchPredicate) other).keywords)); // state check
    }

}
