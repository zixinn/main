package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;

/**
 * Lists all facilitators in Mod Manager to the user.
 */
public class LessonListCommand extends LessonCommand {

    public static final String MESSAGE_SUCCESS = "Listed all lessons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Lesson> lessons = model.getLessons();
        String lessonListString;
        if (lessons.size() == 0) {
            lessonListString = "There is currently no lessons";
        } else {
            lessonListString = "Lessons include:\n";
            for (Lesson lesson : lessons) {
                lessonListString = lessonListString + "\u2022 " + lesson + "\n";
            }
        }
        return new CommandResult(lessonListString, CommandType.LESSON);
    }
}
