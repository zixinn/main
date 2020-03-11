package seedu.address.model.task;

public interface TaskInterface extends Comparable {
    public String getDescription();

    public boolean isTaskDone();
    public boolean markAsDone();

    public String getTimeOutput();

    public String toString();
}
