package seedu.address.model.task;

import java.util.ArrayList;
import java.util.List;

public class TaskListStub implements TaskListInterface {

    List<TaskInterface> tasks = new ArrayList<TaskInterface>();
    
    public void addTask(TaskInterface task) {
        tasks.add(new TaskStub());
    }

    public void printTasks() {
        System.out.println("Task is printed");
    }

    public TaskListInterface viewTasksByName() {

    }

    public TaskListInterface viewTasksByDay(int day) {

    }
    public TaskListInterface viewTasksByMonth(int month) {

    }
    public TaskListInterface viewTasksByYear(int year) {

    }
    public TaskListInterface viewUpcomingTasks() {

    }

    public void modifyTask(int taskNumber) {

    }
    public void makeTaskDone(int taskNumber) {

    }

    public void deleteTask(int taskNumber) {

    }
}
