package seedu.address.model.task;

/**
 * Interface for Task class.
 */
public interface TaskInterface extends Comparable {
    String getDescription();
    String getModCode();

    boolean isTaskDone();
    boolean markAsDone();

    String getTimeOutput();

    String toString();
}
