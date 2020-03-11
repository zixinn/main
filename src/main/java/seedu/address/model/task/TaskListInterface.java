package seedu.address.model.task;

import java.time.LocalDate;
import java.util.List;

public interface TaskListInterface {
    public void addTask(TaskInterface task);

    public void printTasks();
    public TaskListInterface viewTasksByName(String word);
    public TaskListInterface viewTasksByDay(int day);
    public TaskListInterface viewTasksByMonth(int month);
    public TaskListInterface viewTasksByYear(int year);
    public TaskListInterface viewUpcomingTasks();

    public void modifyTask(int taskNumber);
    public void makeTaskDone(int taskNumber);

    public void deleteTask(int taskNumber);
}
