package seedu.address.logic.commands.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.testutil.LessonBuilder;


public class LessonAddCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonAddCommand(null));
    }

    @Test
    public void execute_lessonAcceptedByModel_andSuccessful() throws Exception {
        Lesson lessonToAdd = new LessonBuilder().withLessonType("SEC").withStartTime("19:00").withEndTime("20:00")
                .build();
        String expectedString = String.format(LessonAddCommand.MESSAGE_SUCCESS, lessonToAdd);
        LessonAddCommand command = new LessonAddCommand(lessonToAdd);
        CommandResult commandResult = command.execute(model);
        assertEquals(expectedString, commandResult.getFeedbackToUser());
        assertTrue(model.hasLesson(lessonToAdd));
    }

    @Test
    public void execute_duplicateLesson_throwsCommandException() {
        Lesson lessonToAdd = new LessonBuilder().build();
        LessonAddCommand command = new LessonAddCommand(lessonToAdd);
        assertThrows(CommandException.class,
                LessonAddCommand.MESSAGE_DUPLICATE_LESSON, () -> command.execute(model));
    }

    @Test
    public void execute_moduleDoesNotExist_throwsCommandException() {
        Lesson lessonToAdd = new LessonBuilder().withModuleCode("CS9000").build();
        LessonAddCommand command = new LessonAddCommand(lessonToAdd);
        assertThrows(CommandException.class,
                LessonAddCommand.MESSAGE_INVALID_MODULE_CODE, () -> command.execute(model));
    }

    @Test
    public void equals() {
        Lesson cs2103t = new LessonBuilder().build();
        Lesson cs9000 = new LessonBuilder().withModuleCode("CS9000").build();
        LessonAddCommand addCs2103TCommand = new LessonAddCommand(cs2103t);
        LessonAddCommand addCs9000Command = new LessonAddCommand(cs9000);

        // same object should return true
        assertTrue(addCs2103TCommand.equals(addCs2103TCommand));

        // same values should return true
        LessonAddCommand cs2103tCopy = new LessonAddCommand(cs2103t);
        assertTrue(addCs2103TCommand.equals(cs2103tCopy));

        // different types should return false
        assertFalse(addCs2103TCommand.equals(1));

        // null should return false
        assertFalse(addCs2103TCommand.equals(null));

        // different lesson should return false
        assertFalse(addCs2103TCommand.equals(addCs9000Command));
    }
}
