package seedu.address.model.task;

/**
 * Interface for a TaskList, which is a List of Tasks.
 */
public interface TaskListInterface {
    void addTask(Task task);

    void printTasks();
    TaskListInterface viewTasksByName(String word);
    TaskListInterface viewTasksByDay(int day);
    TaskListInterface viewTasksByMonth(int month);
    TaskListInterface viewTasksByYear(int year);
    TaskListInterface viewUpcomingTasks();

    void modifyTask(int taskNumber);
    void makeTaskDone(int taskNumber);

    void deleteTask(int taskNumber);
}
