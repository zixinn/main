package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;

/**
 * Deletes a lesson identified using it's displayed index from Mod Manager.
 */
public class LessonDeleteCommand extends LessonCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_CLASS + " " + COMMAND_WORD_DELETE
            + ": Deletes the lesson identified by the index number used in the displayed lesson list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_GROUP_CLASS + " " + COMMAND_WORD_DELETE + " 1";

    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX =
            "The lesson index provided is invalid";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s";

    private final Index targetIndex;

    public LessonDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lessons = model.getLessons();
        if (targetIndex.getZeroBased() >= lessons.size()) {
            throw new CommandException(MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToDelete = lessons.get(targetIndex.getZeroBased());
        model.removeLesson(lessonToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete),
                CommandType.LESSON);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((LessonDeleteCommand) other).targetIndex)); // state check
    }
}
