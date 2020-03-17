package seedu.address.model.util;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.Optional;

/**
 * A comparator to compare two items in a Calendar day
 */
public class DailySchedulableComparator implements Comparator<DailySchedulableInterface> {

    @Override
    public int compare(DailySchedulableInterface o1, DailySchedulableInterface o2) {
        Optional<LocalTime> o1Time = o1.getComparableTime();
        Optional<LocalTime> o2Time = o2.getComparableTime();

        if (o1Time.isEmpty()) {
            return -1;
        }

        if (o2Time.isEmpty()) {
            return 1;
        }

        return o1Time.get().compareTo(o2Time.get());
    }
}
