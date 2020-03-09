package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.modulecode.ModuleCode;

/**
 * Represents a Lesson in Mod Manager.
 */
public class Lesson {
    private ModuleCode moduleCode;
    private LessonType type;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String venue; // optional
    private Facilitator facilitator; // optional

    public Lesson(ModuleCode moduleCode, LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        requireAllNonNull(moduleCode, type, day, startTime, endTime);
        this.moduleCode = moduleCode;
        this.type = type;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = null;
        this.facilitator = null;
    }

    public Lesson(ModuleCode moduleCode, LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime,
                  String venue, Facilitator facilitator) {
        requireAllNonNull(moduleCode, type, day, startTime, endTime);
        this.moduleCode = moduleCode;
        this.type = type;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.facilitator = facilitator;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public LessonType getType() {
        return type;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getVenue() {
        return venue;
    }

    public Facilitator getFacilitator() {
        return facilitator;
    }

    public boolean doesFacilitatorExist() {
        return getFacilitator() != null;
    }

    public boolean doesVenueExist() {
        return getVenue() != null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        boolean isSameVenue = false;
        boolean isSameFacilitator = false;

        if (otherLesson.doesFacilitatorExist() && doesFacilitatorExist()) {
            isSameFacilitator = otherLesson.getFacilitator().equals(getFacilitator());
        } else if (!otherLesson.doesFacilitatorExist() && !doesFacilitatorExist()) {
            isSameFacilitator = true;
        }

        if (otherLesson.doesVenueExist() && doesVenueExist()) {
            isSameVenue = otherLesson.getVenue().equals(getVenue());
        } else if (!otherLesson.doesVenueExist() && !doesVenueExist()) {
            isSameVenue = true;
        }

        return otherLesson.getModuleCode().equals(getModuleCode())
                && otherLesson.getType().equals(getType())
                && otherLesson.getDay().equals(getDay())
                && otherLesson.getStartTime().equals(getStartTime())
                && otherLesson.getEndTime().equals(getEndTime())
                && isSameFacilitator
                && isSameVenue;
    }
}

/**
 * Represents the different types of lessons.
 */
enum LessonType {
    LEC, TUT, SEC, REC, LAB
}
