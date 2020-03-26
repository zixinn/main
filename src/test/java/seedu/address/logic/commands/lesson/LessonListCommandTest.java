package seedu.address.logic.commands.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;

/**
 * Contains integration tests (interaction with the Model) for {@code LessonListCommand}.
 */
public class LessonListCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_lessonList_success() {
        LessonListCommand command = new LessonListCommand();
        CommandResult result = command.execute(model);
        String resultString = result.getFeedbackToUser();

        List<Lesson> lessons = model.getLessons();
        String expectedString;
        if (lessons.size() == 0) {
            expectedString = "There is currently no lessons";
        } else {
            expectedString = "Lessons include:\n";
            int index = 1;
            for (Lesson lesson : lessons) {
                expectedString = expectedString + index + ". " + lesson + "\n";
                index++;
            }
        }
        assertEquals(resultString, expectedString);
    }
}
