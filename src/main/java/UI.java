public class UI {
    static String line = "____________________________________________________________\n";
    public void greet() {
        System.out.println(line + "Hello! I'm Hailey :)\n"
                + "What can I do for you?\n" + line);
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
}
