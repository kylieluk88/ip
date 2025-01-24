import java.sql.Array;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markDone(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.markAsDone();
            System.out.println(task);
        }
    }

    public void unmarkDone(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.unmarkAsDone();
            System.out.println(task);
        }
    }

    public void deleteTask(int taskNumber) throws HaileyException {
        tasks.remove(taskNumber);
    }

    public String printTasks() {
        if (tasks.size() == 0) {
            return "No tasks for now, relax!";
        } else {
            StringBuilder taskList = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                taskList.append((i + 1)).append(". ").
                append(tasks.get(i).toString()).append("\n");
            }
            return taskList.toString();
        }
    }

    public int getCount() {
        return tasks.size();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }
}
