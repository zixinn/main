package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.LessonBuilder;

public class LessonTest {

    @Test
    public void equals() {
        Lesson lesson = new LessonBuilder().build();
        Lesson anotherLesson = new LessonBuilder().withModuleCode("GEQ1000").withLessonType("REC").withDay("MONDAY")
                .withStartTime("09:00").withEndTime("10:00").withVenue("club").build();

        // same object should return true
        assertTrue(lesson.equals(lesson));

        // same values should return true
        Lesson lessonCopy = new LessonBuilder().build();
        assertTrue(lesson.equals(lessonCopy));

        // null should return false
        assertFalse(lesson.equals(null));

        // different type should return false
        assertFalse(lesson.equals(1));

        // different lesson should return false
        assertFalse(lesson.equals(anotherLesson));

        // different module code should return false
        anotherLesson = new LessonBuilder().withModuleCode("CS9000").build();
        assertFalse(lesson.equals(anotherLesson));

        // different type should return false
        anotherLesson = new LessonBuilder().withLessonType("LAB").build();
        assertFalse(lesson.equals(anotherLesson));

        // different day and time should return false
        anotherLesson = new LessonBuilder().withDay("TUESDAY").withStartTime("05:00").withEndTime("07:00").build();
        assertFalse(lesson.equals(anotherLesson));

        // different venue should return false
        anotherLesson = new LessonBuilder().withVenue("Park").build();
        assertFalse(lesson.equals(anotherLesson));
    }
}
