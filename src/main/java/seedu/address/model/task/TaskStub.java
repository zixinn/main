package seedu.address.model.task;

/**
 * Stub class for Task.
 */
public class TaskStub implements TaskInterface {
    @Override
    public String getDescription() {
        return "CS3230 Programming Assignment 2";
    }

    @Override
    public boolean isTaskDone() {
        return false;
    }

    @Override
    public boolean markAsDone() {
        return true; // can only call once
    }

    @Override
    public String getTimeOutput() {
        return "21/03/2020 18:00";
    }

    @Override
    public int compareTo(Object o) {
        return 0; // ?
    }
}
