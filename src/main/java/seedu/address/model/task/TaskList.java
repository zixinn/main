package seedu.address.model.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList is a wrapper class for a List of Tasks.
 */
public class TaskList implements TaskListInterface {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /*
    protected TaskList(Storage storage) throws FileNotFoundException {
        this.tasks = storage.getAllTasksFromFile();
    }
    */

    @Override
    public void addTask(Task task) {
        StringBuilder message = new StringBuilder();
        tasks.add(task);
        message.append("Got it. I've added this task: \n");
        message.append(task + "\n");
        message.append("Now you have " + tasks.size() + " tasks in the list.");
        //return message.toString();
    }

    @Override
    public void printTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        //return result.toString();
    }

    @Override
    public TaskList viewTasksByName(String word) {
        return new TaskList(tasks.stream()
                .filter(task -> task.getDescription().contains(word))
                .collect(Collectors.toList()));
    }

    @Override
    public TaskList viewTasksByDay(int day) {
        return new TaskList(tasks.stream()
                .filter(task -> task.isTimeAvailable())
                .filter(task -> task.getTime().getDayOfMonth() == day)
                .collect(Collectors.toList()));
    }

    @Override
    public TaskList viewTasksByMonth(int month) {
        return new TaskList(tasks.stream()
                .filter(task -> task.isTimeAvailable())
                .filter(task -> task.getTime().getMonthValue() == month)
                .collect(Collectors.toList()));
    }

    @Override
    public TaskList viewTasksByYear(int year) {
        return new TaskList(tasks.stream()
                .filter(task -> task.isTimeAvailable())
                .filter(task -> task.getTime().getYear() == year)
                .collect(Collectors.toList()));
    }

    @Override
    public TaskList viewUpcomingTasks() {
        List<Task> sortedResult = new ArrayList<Task>(tasks);
        Collections.sort(sortedResult);
        return new TaskList(sortedResult);
    }

    protected List<Task> viewAllTasks() {
        return tasks;
    }

    protected int getTasksLength() {
        return tasks.size();
    }

    protected Task getTaskAtPosition(int taskNumber) {
        return tasks.get(taskNumber);
    }

    @Override
    public void modifyTask(int taskNumber) {
        return;
    }

    @Override
    public void makeTaskDone(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        //return "Nice! I've marked this task as done: " + "\n" + tasks.get(taskNumber);
    }

    @Override
    public void deleteTask(int taskNumber) {
        StringBuilder message = new StringBuilder();
        message.append("Noted. I've removed this task: \n");
        message.append(tasks.get(taskNumber) + "\n");
        tasks.remove(taskNumber);
        message.append("Now you have " + tasks.size() + " tasks in the list. \n");
        //return message.toString();
    }

}
