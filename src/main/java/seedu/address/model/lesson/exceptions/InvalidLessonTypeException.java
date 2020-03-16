package seedu.address.model.lesson.exceptions;

public class InvalidLessonTypeException extends RuntimeException {
    public InvalidLessonTypeException() {
        super("Lesson type provided is invalid.");
    }
}
