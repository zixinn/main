package seedu.address.model.lesson;

import seedu.address.model.module.ModuleCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A list of lessons.
 */
public class LessonList {

    private List<Lesson> lessons;

    public LessonList() {
        this.lessons = new ArrayList<>();
    }

    /**
     * Adds a lesson to the list of lessons.
     * @param lesson The lesson to be added.
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

}
