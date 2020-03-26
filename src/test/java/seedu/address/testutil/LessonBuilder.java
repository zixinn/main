package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonType;
import seedu.address.model.module.ModuleCode;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final String DEFAULT_LESSON_TYPE = "LEC";
    public static final String DEFAULT_DAY = "FRIDAY";
    public static final String DEFAULT_START_TIME = "14:00";
    public static final String DEFAULT_END_TIME = "16:00";
    public static final String DEFAULT_VENUE = "I3";

    private ModuleCode moduleCode;
    private LessonType lessonType;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String venue;

    public LessonBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        lessonType = LessonType.valueOf(DEFAULT_LESSON_TYPE);
        day = DayOfWeek.valueOf(DEFAULT_DAY);
        startTime = LocalTime.parse(DEFAULT_START_TIME);
        endTime = LocalTime.parse(DEFAULT_END_TIME);
        venue = DEFAULT_VENUE;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code LessonType} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withLessonType(String type) {
        this.lessonType = LessonType.valueOf(type);
        return this;
    }

    /**
     * Sets the {@code day} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withDay(String day) {
        this.day = DayOfWeek.valueOf(day);
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
        return this;
    }

    /**
     * Sets the {@code venue} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withVenue(String venue) {
        this.venue = venue;
        return this;
    }

    public Lesson build() {
        return new Lesson(moduleCode, lessonType, day, startTime, endTime, venue);
    }


}
