package seedu.address.model.task;

/**
 * Interface for Task class.
 */
public interface TaskInterface extends Comparable {
    String getDescription();

    boolean isTaskDone();
    boolean markAsDone();

    String getTimeOutput();

    String toString();
}
