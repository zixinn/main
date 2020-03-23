package seedu.address.storage;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonType;
import seedu.address.model.module.ModuleCode;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Class's %s field is missing!";

    private final String moduleCode;
    private final String type;
    private final String day;
    private final String startTime;
    private final String endTime;
    private final String venue;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("moduleCode") String moduleCode, @JsonProperty("type") String type,
                             @JsonProperty("day") String day, @JsonProperty("startTime") String startTime,
                             @JsonProperty("endTime") String endTime, @JsonProperty("venue") String venue) {
        this.moduleCode = moduleCode;
        this.type = type;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        moduleCode = source.getModuleCode().toString();
        type = source.getType().toString();
        day = source.getDay().toString();
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
        venue = source.getVenue();
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }

        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }

        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonType.class.getSimpleName()));
        }

        if (!Lesson.isValidType(type)) {
            throw new IllegalValueException(LessonType.MESSAGE_CONSTRAINTS);
        }

        final LessonType modelType = Lesson.convertStringToLessonType(type);

        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DayOfWeek.class.getSimpleName()));
        }

        if (Arrays.stream(DayOfWeek.values()).noneMatch(x -> x.toString().equals(day))) {
            throw new IllegalValueException(Lesson.MESSAGE_CONSTRAINTS_DAY);
        }

        final DayOfWeek modelDay = DayOfWeek.valueOf(day);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalTime.class.getSimpleName()));
        }

        if (!Lesson.isValidTimeFormat(startTime)) {
            throw new IllegalValueException(Lesson.MESSAGE_CONSTRAINTS_TIME);
        }

        final LocalTime modelStartTime = LocalTime.parse(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalTime.class.getSimpleName()));
        }

        if (!Lesson.isValidTimeFormat(endTime)) {
            throw new IllegalValueException(Lesson.MESSAGE_CONSTRAINTS_TIME);
        }

        final LocalTime modelEndTime = LocalTime.parse(endTime);

        return new Lesson(modelModuleCode, modelType, modelDay, modelStartTime, modelEndTime, venue);
    }
}
