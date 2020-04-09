package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.ModuleCodesContainKeywordPredicate;
import seedu.address.model.lesson.DayAndTime;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonType;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.util.action.DoableActionType;
import seedu.address.model.util.action.LessonAction;

/**
 * Edits the details of an existing lesson in Mod Manager.
 */
public class LessonEditCommand extends LessonCommand {
    public static final String MESSAGE_USAGE = COMMAND_GROUP_CLASS + " " + COMMAND_WORD_EDIT
            + ": Edits the details of the lesson identified "
            + "by the index number used in the displayed lesson list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MODULE_CODE + " MOD_CODE "
            + "[" + PREFIX_MODULE_CODE + " NEW_MOD_CODE] "
            + "[" + PREFIX_TYPE + " CLASS_TYPE] "
            + "[" + PREFIX_AT + " DAY START_TIME END_TIME] "
            + "[" + PREFIX_VENUE + " VENUE] \n"
            + "Example: " + COMMAND_GROUP_CLASS + " " + COMMAND_WORD_EDIT + " 1 "
            + PREFIX_MODULE_CODE + " CS9000 "
            + PREFIX_AT + " TUESDAY 01:00 02:00";

    public static final String MESSAGE_EDIT_LESSON_SUCCESS = "Edited Lesson: %1$s";
    public static final String MESSAGE_DUPLICATE_LESSON = "This lesson already exists in Mod Manager";
    public static final String MESSAGE_INVALID_MODULE_CODE = "Module does not exist";
    public static final String MESSAGE_INVALID_TIME_RANGE = "The edited lesson clashes with another lesson";

    private final Index index;
    private final EditLessonDescriptor editLessonDescriptor;
    private final ModuleCode target;

    /**
     * @param index of the lesson in the lesson list to edit
     * @param editLessonDescriptor details to edit the lesson with
     */
    public LessonEditCommand(ModuleCode target, Index index, EditLessonDescriptor editLessonDescriptor) {
        requireAllNonNull(index, editLessonDescriptor);
        this.index = index;
        this.target = target;
        this.editLessonDescriptor = new EditLessonDescriptor(editLessonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (index.getZeroBased() >= model.getLessons().size()) {
            throw new CommandException(MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Optional<Module> module = model.findModule(target);
        if (module.isEmpty()) {
            throw new CommandException(MESSAGE_INVALID_MODULE_CODE);
        }

        Lesson lessonToEdit = model.getLessonListForModule(target).get(index.getZeroBased());
        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);

        if (lessonToEdit.equals(editedLesson)) {
            throw new CommandException(MESSAGE_USAGE);
        }

        if (model.hasLesson(editedLesson)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        if (model.findModule(editedLesson.getModuleCode()).isEmpty()) {
            throw new CommandException(MESSAGE_INVALID_MODULE_CODE);
        }

        if (!model.isTimeSlotFree(editedLesson, Optional.of(lessonToEdit))) {
            throw new CommandException(MESSAGE_INVALID_TIME_RANGE);
        }

        model.setLesson(lessonToEdit, editedLesson);
        LessonAction editLessonAction = new LessonAction(lessonToEdit, editedLesson, DoableActionType.EDIT);
        model.addAction(editLessonAction);

        model.updateModule(model.findModule(editedLesson.getModuleCode()));
        model.updateFacilitatorListForModule(
                new ModuleCodesContainKeywordPredicate(editedLesson.getModuleCode().value));
        model.updateTaskListForModule(x -> x.getModuleCode().equals(editedLesson.getModuleCode()));

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
        DayAndTime updatedDayAndTime = editLessonDescriptor.getDayAndTime().orElse(lessonToEdit.getDayAndTime());
        String updatedVenue = editLessonDescriptor.getVenue().orElse(lessonToEdit.getVenue());

        if (editLessonDescriptor.getVenue().isPresent() && editLessonDescriptor.getVenue().get().equals("")) {
            updatedVenue = null;
        } else if (editLessonDescriptor.getVenue().isPresent()) {
            updatedVenue = editLessonDescriptor.getVenue().orElse(lessonToEdit.getVenue());
        }

        return new Lesson(updatedModuleCode, updatedLessonType, updatedDayAndTime,
                updatedVenue);
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
                && target.equals(e.target)
                && editLessonDescriptor.equals(e.editLessonDescriptor);
    }

    /**
     * Stores the details to edit the lesson with. Each non-empty field value will replace the
     * corresponding field value of the lesson.
     */
    public static class EditLessonDescriptor {
        private ModuleCode moduleCode;
        private LessonType type;
        private DayAndTime dayAndTime;
        private String venue; // optional

        public EditLessonDescriptor() {}

        public EditLessonDescriptor(EditLessonDescriptor copy) {
            setModuleCode(copy.moduleCode);
            setLessonType(copy.type);
            setDayAndTime(copy.dayAndTime);
            setVenue(copy.venue);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode, type, dayAndTime, venue);
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

        public void setDayAndTime(DayAndTime dayAndTime) {
            this.dayAndTime = dayAndTime;
        }

        public Optional<DayAndTime> getDayAndTime() {
            return Optional.ofNullable(dayAndTime);
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public Optional<String> getVenue() {
            return Optional.ofNullable(venue);
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
                    && getDayAndTime().equals(e.getDayAndTime())
                    && getVenue().equals(e.getVenue());
        }
    }
}
