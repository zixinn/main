package seedu.address.model.task;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class TaskFilterPredicate implements Predicate<Task> {

    private final HashMap<String, Integer> keywords;

    public TaskFilterPredicate(HashMap<String, Integer> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {

        if (task.getTaskDateTime().isEmpty()) {
            return false; // no time
        }

        boolean checkDay = true;
        boolean checkMonth = true;
        boolean checkYear = true;
        if (keywords.containsKey("day")) {
            checkDay = (task.getTaskDateTime().get().isDateOnThisDay(keywords.get("day")));
        }

        if (keywords.containsKey("day")) {
            checkMonth = (task.getTaskDateTime().get().isDateOnThisMonth(keywords.get("month")));

        }

        if (keywords.containsKey("day")) {
            checkYear = (task.getTaskDateTime().get().isDateOnThisYear(keywords.get("year")));
        }
        System.out.println(checkDay);
        System.out.println(checkMonth);
        System.out.println(checkYear);

        return checkDay && checkMonth && checkYear;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskFilterPredicate // instanceof handles nulls
                && keywords.equals(((TaskFilterPredicate) other).keywords)); // state check
    }

}
