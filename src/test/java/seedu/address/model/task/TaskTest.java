package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testGetDescription_success() {
        assertEquals("Tutorial 7", new Task("Tutorial 7").getDescription());
        assertEquals("Assignment 1", new Task("Assignment 1", "12/04/2020").getDescription());
        assertEquals("Lecture Week 8", new Task("Lecture Week 8", "12/03/2020", "23:59").getDescription());
    }

    @Test
    void markAsDone_InitiallyNotDoneTask_success() {
        Task task = new Task("Tutorial 7");
        assertFalse(task.isTaskDone());
        task.markAsDone();
        assertTrue(task.isTaskDone());
    }

    @Test
    void markAsDone_alreadyDoneTask_notifyTaskMarkedAsDone() {
        Task task = new Task("Tutorial 7");
        assertFalse(task.isTaskDone());
        task.markAsDone();
        assertFalse(task.markAsDone()); // false means Task is already done
    }

    @Test
    void isTaskDone_newlyCreatedTask_false() {
        assertFalse(new Task("Tutorial 7").isTaskDone());
        assertFalse(new Task("Tutorial 7", "12/03/2020").isTaskDone());
        assertFalse(new Task("Tutorial 7", "12/03/2020", "23:59").isTaskDone());
    }

    @Test
    void isTaskDone_markedAsDoneTask_true() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Tutorial 7"));
        tasks.add(new Task("Tutorial 7", "12/03/2020"));
        tasks.add(new Task("Tutorial 7", "13/03/2020", "23:59"));
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).markAsDone();
            assertTrue(tasks.get(i).isTaskDone());
        }

        // duplicating markAsDone
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).markAsDone();
            assertTrue(tasks.get(i).isTaskDone());
        }
    }

    @Test
    void getTime_noTimeAvailableForTask_returnsNull() {
        assertNull(new Task("Tutorial 7").getTime());
    }

    @Test
    void getTime_onlyTaskDateAvailable_success() {
        assertEquals(LocalDate.of(2020, 10, 10).atStartOfDay(),
                new Task("Tutorial 7", "10/10/2020").getTime());
    }

    @Test
    void getTime_bothTaskDateAndTimeAvailable_success() {
        assertEquals(LocalDate.of(2020, 10, 10).atTime(23, 59),
                new Task("Tutorial 7", "10/10/2020", "23:59").getTime());
    }

    @Test
    void isTimeAvailable_taskHasNoTime_false() {
        assertFalse(new Task("Tutorial 7").isTimeAvailable());
    }

    @Test
    void isTimeAvailable_onlyTaskDateAvailable_true() {
        assertTrue(new Task("Tutorial 7", "26/06/2020").isTimeAvailable());
    }

    @Test
    void isTimeAvailable_bothTaskDateAndTimeAvailable_true() {
        assertTrue(new Task("Tutorial 7", "26/06/2020", "00:00").isTimeAvailable());
    }

    @Test
    void getTimeOutput_taskHasNoTime_emptyString() {
        assertEquals("", new Task("Tutorial 7").getTimeOutput());
    }

    @Test
    void getTimeOutput_onlyTaskDateAvailable_success() {
        assertEquals("20/05/2020", new Task("Tutorial 7", "20/05/2020").getTimeOutput());
    }

    @Test
    void getTimeOutput_bothTaskDateAndTimeAvailable_success() {
        assertEquals("20/05/2020 09:00", new Task("Tutorial 7", "20/05/2020", "09:00").getTimeOutput());
    }

    @Test
    void getDateInWeek_taskHasNoTime_emptyString() {
        assertEquals("", new Task("Tutorial 7").getDateInWeek());
    }

    @Test
    void getDateInWeek_onlyTaskDateAvailable_success() {
        assertEquals("Thursday", new Task("Tutorial 7", "19/03/2020").getDateInWeek());
    }

    @Test
    void getDateInWeek_bothTaskDateAndTimeAvailable_success() {
        assertEquals("Friday", new Task("Tutorial 7", "20/03/2020", "00:00").getDateInWeek());
    }

    @Test
    void testToString_taskHasNoTime_success() {
        assertEquals("[\u2718] Tutorial 7" + "\n",
                new Task("Tutorial 7").toString());
    }

    @Test
    void testToString_onlyTaskDateAvailable_success() {
        assertEquals("[\u2718] Tutorial 7 31/03/2020" + "\n",
                new Task("Tutorial 7", "31/03/2020").toString());
    }

    @Test
    void testToString_bothTaskDateAndTimeAvailable_success() {
        assertEquals("[\u2718] Tutorial 7 01/04/2020 00:01" + "\n",
                new Task("Tutorial 7", "01/04/2020", "00:01").toString());
    }

    @Test
    void toDatabase() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void testEquals() {
    }
}