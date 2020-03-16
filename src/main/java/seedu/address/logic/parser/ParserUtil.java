package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Description;
import seedu.address.model.calendar.Calendar;
import seedu.address.model.facilitator.Email;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.Name;
import seedu.address.model.facilitator.Office;
import seedu.address.model.facilitator.Phone;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonType;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.TaskDateTime;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String moduleCode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses a {@code String description} into a {@code description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        String trimmedDescription;
        if (description == null) {
            trimmedDescription = null;
        } else {
            trimmedDescription = description.trim();
            if (!Description.isValidDescription(trimmedDescription)) {
                throw new ParseException(Description.MESSAGE_CONSTRAINTS);
            }
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        String trimmedPhone;
        if (phone == null) {
            trimmedPhone = null;
        } else {
            trimmedPhone = phone.trim();
            if (!Phone.isValidPhone(trimmedPhone)) {
                throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
            }
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String office} into an {@code Office}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code office} is invalid.
     */
    public static Office parseOffice(String office) throws ParseException {
        String trimmedOffice;
        if (office == null) {
            trimmedOffice = null;
        } else {
            trimmedOffice = office.trim();
            if (!Office.isValidOffice(trimmedOffice)) {
                throw new ParseException(Office.MESSAGE_CONSTRAINTS);
            }
        }
        return new Office(trimmedOffice);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        String trimmedEmail;
        if (email == null) {
            trimmedEmail = null;
        } else {
            trimmedEmail = email.trim();
            if (!Email.isValidEmail(trimmedEmail)) {
                throw new ParseException(Email.MESSAGE_CONSTRAINTS);
            }
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses {@code Collection<String> moduleCodes} into a {@code Set<ModuleCode>}.
     */
    public static Set<ModuleCode> parseModuleCodes(Collection<String> moduleCodes) throws ParseException {
        requireNonNull(moduleCodes);
        final Set<ModuleCode> moduleCodeSet = new HashSet<>();
        for (String moduleCode : moduleCodes) {
            moduleCodeSet.add(parseModuleCode(moduleCode));
        }
        return moduleCodeSet;
    }

    /**
     * Parses {@code String lessonType} into a {@code LessonType}.
     */
    public static LessonType parseLessonType(String lessonType) throws ParseException {
        requireNonNull(lessonType);
        String trimmedType = lessonType.trim();
        if (!Lesson.isValidType(lessonType)) {
            throw new ParseException("Class types should be either LEC, TUT, SEC, REC or LAB");
        }
        return Lesson.convertStringToLessonType(lessonType);
    }

    /**
     * Parses {@code String dayAndTime} into a {@code DayOfWeek}.
     */
    public static DayOfWeek parseDay(String dayAndTime) throws ParseException {
        requireNonNull(dayAndTime);
        String trimmed = dayAndTime.trim();
        String day = trimmed.split(" ")[0];
        boolean isDayValid = false;
        DayOfWeek assignedDay;
        for (int i = 0; i < 7; i++) {
            if (DayOfWeek.values()[i].toString().equals(day)) {
                isDayValid = true;
                break;
            }
        }
        if (!isDayValid) {
            throw new ParseException("Day provided should be its full name and in capital");
        }
        return DayOfWeek.valueOf(day);
    }

    /**
     * Parses {@code String dayAndTime} into {@code LocalTime}.
     */
    public static LocalTime parseStartTime(String dayAndTime) throws ParseException, DateTimeParseException {
        requireNonNull(dayAndTime);
        String trimmed = dayAndTime.trim();
        String timeString = trimmed.split(" ")[1];
        try {
            return LocalTime.parse(timeString);
        } catch (DateTimeParseException e) {
            throw new ParseException("Time provided is in the wrong format");
        }
    }

    /**
     * Parses {@code String dayAndTime} into {@code LocalTime}.
     */
    public static LocalTime parseEndTime(String dayAndTime) throws ParseException {
        requireNonNull(dayAndTime);
        String trimmed = dayAndTime.trim();
        String timeString = trimmed.split(" ")[2];
        try {
            return LocalTime.parse(timeString);
        } catch (DateTimeParseException e) {
            throw new ParseException("Time provided is in the wrong format");
        }
    }

    /**
     * Parses {@code String venue} into {@code String}.
     */
    public static String parseVenue(String venue) throws ParseException {
        if (venue.equals("")) {
            throw new ParseException("Venue cannot be empty");
        } else {
            return venue.trim();
        }
    }

    /**
     * Parses {@code String facilName} into {@code Facilitator}.
     */
    public static Facilitator parseFacilitator(String facilName) throws ParseException {
        String facilString = facilName.trim();
        // for now
        return null;
    }

    /**
     * Parses {@code String week} into a {@code Set<ModuleCode>} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code week} is invalid.
     */
    public static String parseWeek(String week) throws ParseException {
        requireNonNull(week);
        String trimmedWeek = week.trim();
        if (trimmedWeek.equals("this") || trimmedWeek.equals("next")) {
            return trimmedWeek;
        } else {
            throw new ParseException(Calendar.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String taskTime} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskTime} is invalid.
     */
    public static TaskDateTime parseDateForTask(String taskTime) throws ParseException {
        String trimmedTime;
        if (taskTime == null) {
            trimmedTime = null;
        } else {
            trimmedTime = taskTime.trim();
            if (!Description.isValidDescription(trimmedTime)) {
                throw new ParseException(Description.MESSAGE_CONSTRAINTS);
            }
        }
        return new TaskDateTime(trimmedTime);
    }

    /**
     * Parses a {@code String taskTime} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskTime} is invalid.
     */
    public static TaskDateTime parseDateTimeForTask(String taskTime, String taskTimeInDay) throws ParseException {
        String trimmedTime;
        if (taskTime == null) {
            trimmedTime = null;
        } else {
            trimmedTime = taskTime.trim();
            if (!TaskDateTime.isValidTaskTime(trimmedTime)) {
                throw new ParseException(TaskDateTime.MESSAGE_CONSTRAINTS);
            }
        }

        String trimmedTimeInDay;
        if (taskTimeInDay == null) {
            trimmedTimeInDay = null;
        } else {
            trimmedTimeInDay = taskTime.trim();
            if (!TaskDateTime.isValidTaskTime(trimmedTimeInDay)) {
                throw new ParseException(TaskDateTime.MESSAGE_CONSTRAINTS);
            }
        }
        return new TaskDateTime(trimmedTime, trimmedTimeInDay);
    }
}
