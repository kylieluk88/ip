import org.w3c.dom.ls.LSOutput;

public class UI {
    static String line = "____________________________________________________________\n";
    public void greet() {
        System.out.println(line + "Hello! I'm Hailey :)\n"
                + "What can I do for you?\n" + line);
    }

    public void help() {
        System.out.println(line + "you can add tasks by:\n" +
                " todo [description]\n" +
                " deadline [description] /by [deadline]\n" +
                " event [description] /from [start] /to [end]\n" +
                "\nother commands:\n" +
                " list //to view your list of tasks\n" +
                " mark [task number] //to mark a task as done\n" +
                " unmark [task number] //to unmark a task as done\n" +
                line);
    }

    public void sayBye() {
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }

    public void addTask(Task task, TaskList tasks) {
        System.out.println(line + "Got it. I've added this task:\n  " + task.toString()
                + "\nNow you have " + tasks.getCount() + " tasks in the list.\n" + line);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(line + "Here are the tasks in your list:\n" + tasks.printTasks() + line);
    }

    public void markDone() {
        System.out.println(line + "Nice! I've marked this task as done:\n");
    }

    public void unmarkDone() {
        System.out.println(line + "OK, I've marked this task as not done yet:\n");
    }

    public void deleteTask(TaskList tasks, int taskNumber) {
        Task task = tasks.getTask(taskNumber);
        System.out.println(line + "okay, I've removed this task:\n" + task.toString() +
                "\nNow you have " + (tasks.getCount() - 1) + " tasks in the list.\n" + line);
    }

    public void errorMessage() {
        System.out.println("type 'help' to see a list of command formats :)\n" + line);
    }

    public void showLoadingError() {
        System.out.println("error loading past tasks");
    }
}
