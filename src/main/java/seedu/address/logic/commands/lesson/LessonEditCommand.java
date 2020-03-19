package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonType;
import seedu.address.model.module.ModuleCode;

/**
 * Edits the details of an existing lesson in Mod Manager.
 */
public class LessonEditCommand extends LessonCommand {
    public static final String MESSAGE_USAGE = COMMAND_GROUP_CLASS + " " + COMMAND_WORD_EDIT
            + ": Edits the details of the lesson identified "
            + "by the index number used in the displayed lesson list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULE_CODE + " MOD_CODE] "
            + "[" + PREFIX_TYPE + " CLASS_TYPE] "
            + "[" + PREFIX_AT + " DAY START_TIME END_TIME] "
            + "[" + PREFIX_VENUE + " VENUE] "
            + "[" + PREFIX_FACIL + " FACILITATOR_NAME]...\n"
            + "Example: " + COMMAND_GROUP_CLASS + " " + COMMAND_WORD_EDIT + " 1 "
            + PREFIX_MODULE_CODE + " CS9000 "
            + PREFIX_AT + " TUESDAY 01:00 02:00";

    public static final String MESSAGE_EDIT_LESSON_SUCCESS = "Edited Lesson: %1$s";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX =
            "The lesson index provided is invalid";

    private final Index index;
    private final EditLessonDescriptor editLessonDescriptor;

    /**
     * @param index of the lesson in the lesson list to edit
     * @param editLessonDescriptor details to edit the lesson with
     */
    public LessonEditCommand(Index index, EditLessonDescriptor editLessonDescriptor) {
        requireAllNonNull(index, editLessonDescriptor);
        this.index = index;
        this.editLessonDescriptor = new EditLessonDescriptor(editLessonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lessons = model.getLessons();
        if (index.getZeroBased() >= lessons.size() || index.getZeroBased() < 0) {
            throw new CommandException(MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToEdit = lessons.get(index.getZeroBased());
        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);

        if (!lessonToEdit.equals(editedLesson) && model.hasLesson(editedLesson)) {
            throw new CommandException("This lesson already exists in Mod Manager");
        }

        model.setLesson(lessonToEdit, editedLesson);
        return new CommandResult(String.format(MESSAGE_EDIT_LESSON_SUCCESS, editedLesson), CommandType.LESSON);
    }

    /**
     * Creates and returns a {@code Lesson} with the details of {@code lessonToEdit}
     * edited with {@code editLessonDescriptor}.
     */
    private static Lesson createEditedLesson(Lesson lessonToEdit, EditLessonDescriptor editLessonDescriptor) {
        assert lessonToEdit != null;
        ModuleCode updatedModuleCode = editLessonDescriptor.getModuleCode().orElse(lessonToEdit.getModuleCode());
        LessonType updatedLessonType = editLessonDescriptor.getLessonType().orElse(lessonToEdit.getType());
        DayOfWeek updatedDay = editLessonDescriptor.getDay().orElse(lessonToEdit.getDay());
        LocalTime updatedStartTime = editLessonDescriptor.getStartTime().orElse(lessonToEdit.getStartTime());
        LocalTime updatedEndTime = editLessonDescriptor.getEndTime().orElse(lessonToEdit.getEndTime());
        String updatedVenue = editLessonDescriptor.getVenue().orElse(lessonToEdit.getVenue());
        Facilitator updatedFacilitator = editLessonDescriptor.getFacilitator().orElse(lessonToEdit.getFacilitator());

        return new Lesson(updatedModuleCode, updatedLessonType, updatedDay, updatedStartTime, updatedEndTime,
                updatedVenue, updatedFacilitator);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonEditCommand)) {
            return false;
        }

        // state check
        LessonEditCommand e = (LessonEditCommand) other;
        return index.equals(e.index)
                && editLessonDescriptor.equals(e.editLessonDescriptor);
    }

    /**
     * Stores the details to edit the lesson with. Each non-empty field value will replace the
     * corresponding field value of the lesson.
     */
    public static class EditLessonDescriptor {
        private ModuleCode moduleCode;
        private LessonType type;
        private DayOfWeek day;
        private LocalTime startTime;
        private LocalTime endTime;
        private String venue; // optional
        private Facilitator facilitator; // optional

        public EditLessonDescriptor() {}

        public EditLessonDescriptor(EditLessonDescriptor copy) {
            setModuleCode(copy.moduleCode);
            setLessonType(copy.type);
            setDay(copy.day);
            setStartTime(copy.startTime);
            setEndTime(copy.endTime);
            setVenue(copy.venue);
            setFacilitator(copy.facilitator);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode, type, day, startTime, endTime, venue, facilitator);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setLessonType(LessonType type) {
            this.type = type;
        }

        public Optional<LessonType> getLessonType() {
            return Optional.ofNullable(type);
        }

        public void setDay(DayOfWeek day) {
            this.day = day;
        }

        public Optional<DayOfWeek> getDay() {
            return Optional.ofNullable(day);
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public Optional<LocalTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }

        public Optional<LocalTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public Optional<String> getVenue() {
            return Optional.ofNullable(venue);
        }

        public void setFacilitator(Facilitator facilitator) {
            this.facilitator = facilitator;
        }

        public Optional<Facilitator> getFacilitator() {
            return Optional.ofNullable(facilitator);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditLessonDescriptor)) {
                return false;
            }

            EditLessonDescriptor e = (EditLessonDescriptor) other;

            return getModuleCode().equals(e.getModuleCode())
                    && getLessonType().equals(e.getLessonType())
                    && getDay().equals(e.getDay())
                    && getStartTime().equals(e.getStartTime())
                    && getEndTime().equals(e.getEndTime())
                    && getVenue().equals(e.getVenue())
                    && getFacilitator().equals(e.getFacilitator());
        }
    }
}