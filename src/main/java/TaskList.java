public class TaskList {
    private Task[] tasks;
    private int count;

    public TaskList() {
        this.tasks = new Task[100];
        this.count = 0;
    }

    public void addTask(String description) {
        tasks[count] = new Task(description);
        count++;
    }

    public String printTasks() {
        if (count == 0) {
            return "No tasks for now, relax!";
        } else {
            StringBuilder taskList = new StringBuilder();
            for (int i = 0; i < count; i++) {
                taskList.append((i + 1)).append(". ").
                append(tasks[i].toString()).append("\n");
            }
            return taskList.toString();
        }
    }
}
