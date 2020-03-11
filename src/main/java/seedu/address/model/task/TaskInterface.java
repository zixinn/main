package seedu.address.model.task;

public interface TaskInterface extends Comparable {
    public String getDescription();
    public boolean isTaskDone():
    public String getDateOutput();
    public String getTimeOutput();
    public String toString();
    public String toDatabase();
}
