package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.module.ModuleCode;

/**
 * Deletes a lesson identified using it's displayed index from Mod Manager.
 */
public class LessonDeleteCommand extends LessonCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_CLASS + " " + COMMAND_WORD_DELETE
            + ": Deletes the lesson identified by the index number used in the displayed lesson list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_GROUP_CLASS + " " + COMMAND_WORD_DELETE + " 1\n"
            + " or \n"
            + "Parameters: " + PREFIX_INDEX + " INDEX (must be a positive number) " + PREFIX_MODULE_CODE + " MOD_CODE\n"
            + "Example: " + COMMAND_GROUP_CLASS + " " + COMMAND_WORD_DELETE + " " + PREFIX_INDEX
            + " 1 " + PREFIX_MODULE_CODE + " CS2103T\n";

    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX =
            "The lesson index provided is invalid";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s";

    public static final String MESSAGE_INVALID_MODULE_CODE = "Module code provided is invalid";

    private final Index targetIndex;
    private Optional<ModuleCode> moduleCode;

    public LessonDeleteCommand(Index targetIndex, Optional<ModuleCode> moduleCode) {
        this.targetIndex = targetIndex;
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (moduleCode.isEmpty()) {
            List<Lesson> lessons = model.getLessons();
            if (targetIndex.getZeroBased() >= lessons.size()) {
                throw new CommandException(MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
            }

            Lesson lessonToDelete = lessons.get(targetIndex.getZeroBased());
            model.removeLesson(lessonToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete),
                    CommandType.LESSON);
        } else {
            if (!model.hasModuleCode(moduleCode.get().toString())) {
                throw new CommandException(MESSAGE_INVALID_MODULE_CODE);
            }
            ObservableList<Lesson> lessons = model.getLessonListForModule(moduleCode.get());
            if (targetIndex.getZeroBased() >= lessons.size()) {
                throw new CommandException(MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
            }

            Lesson lessonToDelete = lessons.get(targetIndex.getZeroBased());
            model.removeLesson(lessonToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete),
                    CommandType.LESSON);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((LessonDeleteCommand) other).targetIndex)); // state check
    }
}
