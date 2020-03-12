package seedu.address.model.task;

/**
 * Interface for Task class.
 */
public interface TaskInterface extends Comparable {
    public String getDescription();

    public boolean isTaskDone();
    public boolean markAsDone();

    public String getTimeOutput();

    public String toString();
}
