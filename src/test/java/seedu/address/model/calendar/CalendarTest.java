package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class CalendarTest {

    @Test
    public void getWeek() {
        LocalDate localDate = LocalDate.parse("2020-03-12");
        Calendar calendar = new Calendar(localDate);
        Calendar calendar0 = new Calendar(LocalDate.parse("2020-03-09"));
        Calendar calendar1 = new Calendar(LocalDate.parse("2020-03-10"));
        Calendar calendar2 = new Calendar(LocalDate.parse("2020-03-11"));
        Calendar calendar4 = new Calendar(LocalDate.parse("2020-03-13"));
        Calendar calendar5 = new Calendar(LocalDate.parse("2020-03-14"));
        Calendar calendar6 = new Calendar(LocalDate.parse("2020-03-15"));
        Calendar[] calendars = calendar.getWeek();
        assertTrue(calendars[0].equals(calendar0));
        assertTrue(calendars[1].equals(calendar1));
        assertTrue(calendars[2].equals(calendar2));
        assertTrue(calendars[3].equals(calendar));
        assertTrue(calendars[4].equals(calendar4));
        assertTrue(calendars[5].equals(calendar5));
        assertTrue(calendars[6].equals(calendar6));

    }

    @Test
    public void equals() {
        LocalDate localDate = LocalDate.parse("2020-02-02");
        Calendar calendar = new Calendar(localDate);
        Calendar otherCalendar = new Calendar(localDate);

        //same date -> returns true
        assertTrue(calendar.equals(otherCalendar));

        //same object -> returns true
        assertTrue(calendar.equals(calendar));

        //null -> returns false
        assertFalse(calendar.equals(null));

        //different calendar -> return false
        Calendar diffCalendar = new Calendar(LocalDate.parse("2020-03-03"));
        assertFalse(calendar.equals(diffCalendar));
    }
}
