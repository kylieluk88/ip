public class UI {
    static String line = "____________________________________________________________\n";
    public void greet() {
        System.out.println(line + "Hello! I'm Hailey :)\n"
                + "What can I do for you?\n" + line);
    }

    public void sayBye() {
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }

    public void addTask(String task) {
        System.out.println(line + "added: " + task + "\n" + line);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(line + tasks.printTasks() + line);
    }
}
