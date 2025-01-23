public class TaskList {
    private String[] tasks;
    private int count;

    public TaskList() {
        this.tasks = new String[100];
        this.count = 0;
    }

    public void addTask(String task) {
        tasks[count] = task;
        count++;
    }

    public String printTasks() {
        if (count == 0) {
            return "No tasks for now, relax!";
        } else {
            StringBuilder taskList = new StringBuilder();
            for (int i = 0; i < count; i++) {
                taskList.append((i + 1)).append(". ").append(tasks[i]).append("\n");
            }
            return taskList.toString();
        }
    }
}
