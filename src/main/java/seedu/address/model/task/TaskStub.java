package seedu.address.model.task;

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
    public String getDateOutput() {
        return "21/03/2020";
    }

    @Override
    public String getTimeOutput() {
        return "18:00";
    }

    @Override
    public String toDatabase() {
        return 0 + " | " + getDescription() + " | " + getDateOutput() + " | " +  getTimeOutput();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
