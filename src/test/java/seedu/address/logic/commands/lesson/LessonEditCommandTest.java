package seedu.address.logic.commands.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditLessonDescriptorBuilder;
import seedu.address.testutil.LessonBuilder;

public class LessonEditCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedLessonList_success() {
        Lesson editedLesson = new LessonBuilder().withModuleCode("GEQ1000").withLessonType("TUT").withDay("SUNDAY")
                .withStartTime("01:00").withEndTime("02:00").withVenue("Home").build();
        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(editedLesson).build();
        LessonEditCommand command = new LessonEditCommand(new ModuleCode("CS2103T"), INDEX_FIRST, descriptor);
        String expectedMessage = String.format(LessonEditCommand.MESSAGE_EDIT_LESSON_SUCCESS, editedLesson);
        expectedModel.setLesson(model.getLessons().get(0), editedLesson);
        assertCommandSuccess(command, model, expectedMessage, CommandType.LESSON, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedLessonList_success() {
        Lesson editedLesson = new LessonBuilder().withModuleCode("GEQ1000").withDay("SUNDAY")
                .withStartTime("01:00").withEndTime("02:00").build();
        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(editedLesson).build();
        LessonEditCommand command = new LessonEditCommand(new ModuleCode("CS2103T"), INDEX_FIRST, descriptor);
        String expectedMessage = String.format(LessonEditCommand.MESSAGE_EDIT_LESSON_SUCCESS, editedLesson);
        expectedModel.setLesson(model.getLessons().get(0), editedLesson);
        assertCommandSuccess(command, model, expectedMessage, CommandType.LESSON, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedLessonList_throwsException() {
        LessonEditCommand command = new LessonEditCommand(new ModuleCode("CS2103T"), INDEX_FIRST,
                new LessonEditCommand.EditLessonDescriptor());
        assertCommandFailure(command, model, LessonEditCommand.MESSAGE_USAGE);
    }

    @Test
    public void execute_invalidIndexLessonList_throwsException() {
        Index outOfBound = Index.fromOneBased(model.getLessons().size() + 1);
        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withLessonType("REC")
                .build();
        LessonEditCommand command = new LessonEditCommand(new ModuleCode("CS2103T"), outOfBound, descriptor);
        assertCommandFailure(command, model, LessonEditCommand.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateLessonLessonList_throwsException() {
        Lesson lesson = model.getLessons().get(INDEX_FIRST.getZeroBased());
        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(lesson).build();
        LessonEditCommand command = new LessonEditCommand(new ModuleCode("GEQ1000"), INDEX_FIRST, descriptor);
        assertCommandFailure(command, model, LessonEditCommand.MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_moduleDoesNotExistLessonList_throwsException() {
        Lesson editedLesson = new LessonBuilder().withModuleCode("CS9000").build();
        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(editedLesson).build();
        LessonEditCommand command = new LessonEditCommand(new ModuleCode("CS2103T"), INDEX_FIRST, descriptor);
        assertCommandFailure(command, model, LessonEditCommand.MESSAGE_INVALID_MODULE_CODE);
    }

    @Test
    public void equals() {
        Lesson editedLesson = new LessonBuilder().withModuleCode("GEQ1000").build();
        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(editedLesson).build();
        final LessonEditCommand command = new LessonEditCommand(new ModuleCode("CS2103T"), INDEX_FIRST, descriptor);

        // same values should return true
        LessonEditCommand.EditLessonDescriptor copy = new EditLessonDescriptorBuilder(editedLesson).build();
        LessonEditCommand commandCopy = new LessonEditCommand(new ModuleCode("CS2103T"), INDEX_FIRST, copy);
        assertTrue(command.equals(commandCopy));

        // same object should return true
        assertTrue(command.equals(command));

        // different types should return false
        assertFalse(command.equals(1));

        // null should return false
        assertFalse(command.equals(null));

        // different module codes should return false
        LessonEditCommand anotherCommand = new LessonEditCommand(new ModuleCode("GEQ1000"), INDEX_FIRST, descriptor);
        assertFalse(command.equals(anotherCommand));

        // different indices should return false
        anotherCommand = new LessonEditCommand(new ModuleCode("CS2103T"), INDEX_SECOND, descriptor);
        assertFalse(command.equals(anotherCommand));

        // different descriptor should return false
        anotherCommand = new LessonEditCommand(new ModuleCode("CS2103T"), INDEX_FIRST,
                new EditLessonDescriptorBuilder().withLessonType("SEC").build());
        assertFalse(command.equals(anotherCommand));

    }
}
