package hailey.task;
import hailey.exception.HaileyException;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as done.
     * @param taskNumber The index of the task to be marked as done.
     */
    public void markDone(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.markAsDone();
            System.out.println(task);
        }
    }

    /**
     * Marks a task as not done.
     * @param taskNumber The index of the task to be unmarked.
     */
    public void unmarkDone(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.unmarkAsDone();
            System.out.println(task);
        }
    }

    /**
     * Deletes a task from the list.
     * @param taskNumber The index of the task to be deleted.
     * @throws HaileyException If the task number is out of bounds.
     */
    public void deleteTask(int taskNumber) throws HaileyException {
        tasks.remove(taskNumber);
    }

    /**
     * Finds and returns tasks that contain the given keyword.
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the formatted list of tasks.
     * @return A string containing all tasks.
     */
    public String printTasks() {
        if (tasks.size() == 0) {
            return "No tasks for now, relax!\n";
        } else {
            StringBuilder taskList = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                taskList.append((i + 1)).append(". ").
                append(tasks.get(i).toString()).append("\n");
            }
            return taskList.toString();
        }
    }

    /**
     * Formats the tasks for saving to a file.
     * @return A formatted string of all tasks.
     */
    public String saveTasks() {
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(tasks.get(i).saveFormat()).append("\n");
        }
        return taskList.toString();
    }

    /**
     * Gets the total number of tasks in the list.
     * @return The count of tasks.
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Retrieves a specific task from the list.
     * @param taskNumber The index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }
}
