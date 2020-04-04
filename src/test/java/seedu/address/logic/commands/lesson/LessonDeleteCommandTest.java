package seedu.address.logic.commands.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.module.ModuleCode;

public class LessonDeleteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexLessonList_success() {
        Lesson lessonToDelete = model.getLessons().get(INDEX_FIRST.getZeroBased());
        LessonDeleteCommand command = new LessonDeleteCommand(INDEX_FIRST, Optional.empty());
        String expectedMessage = String.format(LessonDeleteCommand.MESSAGE_DELETE_LESSON_SUCCESS,
                lessonToDelete);
        expectedModel.removeLesson(lessonToDelete);
        assertCommandSuccess(command, model, expectedMessage, CommandType.LESSON, expectedModel);
    }

    @Test
    public void execute_invalidIndexLessonList_throwsCommandException() {
        Index outOfBound = Index.fromOneBased(model.getLessons().size() + 1);
        LessonDeleteCommand command = new LessonDeleteCommand(outOfBound, Optional.empty());
        assertCommandFailure(command, model, LessonDeleteCommand.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        command = new LessonDeleteCommand(outOfBound, Optional.of(new ModuleCode("CS2103T")));
        assertCommandFailure(command, model, LessonDeleteCommand.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validModuleCodeLessonList_success() {
        Lesson lessonToDelete = model.getLessons().get(INDEX_FIRST.getZeroBased());
        LessonDeleteCommand command = new LessonDeleteCommand(INDEX_FIRST, Optional.of(lessonToDelete.getModuleCode()));
        String expectedMessage = String.format(LessonDeleteCommand.MESSAGE_DELETE_LESSON_SUCCESS,
                lessonToDelete);
        expectedModel.removeLesson(lessonToDelete);
        assertCommandSuccess(command, model, expectedMessage, CommandType.LESSON, expectedModel);
    }

    @Test
    public void execute_invalidModuleCodeLessonList_throwsCommandException() {
        LessonDeleteCommand command = new LessonDeleteCommand(INDEX_FIRST, Optional.of(new ModuleCode("CS9000")));
        assertCommandFailure(command, model, LessonDeleteCommand.MESSAGE_INVALID_MODULE_CODE);
    }

    @Test
    public void equals() {
        LessonDeleteCommand deleteFirstCommand = new LessonDeleteCommand(INDEX_FIRST, Optional.empty());
        LessonDeleteCommand deleteSecondCommand = new LessonDeleteCommand(INDEX_SECOND, Optional.empty());

        // same object should return true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values should return true
        LessonDeleteCommand deleteFirstCommandCopy = new LessonDeleteCommand(INDEX_FIRST, Optional.empty());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different type should return false
        assertFalse(deleteFirstCommand.equals(1));

        // null should return false
        assertFalse(deleteFirstCommand.equals(null));

        // different command should return false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
