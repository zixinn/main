package seedu.address.model.calendar;

import java.time.LocalDate;

/**
 * Represents the Calendar in Mod Manager.
 */
public class Calendar {

    private LocalDate localDate;

    public Calendar(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Returns the index of the day of the week.
     *
     * @return the index of the day of the week.
     */
    public int getDayOfWeek() {
        return localDate.getDayOfWeek().getValue();
    }

    /**
     * Returns an array of days in the same week as the current date.
     *
     * @return the array of days in the same week as the current date.
     */
    public Calendar[] getWeek() {
        Calendar[] week = new Calendar[7];
        int index = getDayOfWeek();
        week[index - 1] = this;
        for (int i = 1; i < index; i++) {
            week[index - 1 - i] = new Calendar(getLocalDate().minusDays(i));
        }

        for (int i = 1; i <= 7 - index; i++) {
            week[index - 1 + i] = new Calendar(getLocalDate().plusDays(i));
        }

        return week;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Calendar)) {
            return false;
        }

        Calendar otherCalendar = (Calendar) other;
        return otherCalendar.getLocalDate().equals(getLocalDate());
    }

}
