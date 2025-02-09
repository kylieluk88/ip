package hailey.ui;

import hailey.task.Task;
import hailey.task.TaskList;

import java.util.ArrayList;

public class Ui {
    public String greet() {
        return  "Hello! I'm Hailey :)\n"
                + "What can I do for you?\n";
    }

    public String showHelp() {
        return "you can add tasks by:\n" +
                " > todo [description]\n" +
                " > deadline [description] /by [deadline]\n" +
                " > event [description] /from [start] /to [end]\n" +
                "* date format: d/M/yyyy HHmm\n" +
                "\nother commands:\n" +
                " > list //to view your list of tasks\n" +
                " > mark [task number] //to mark a task as done\n" +
                " > unmark [task number] //to unmark a task as done\n";
    }

    public String sayBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String printAddMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n  " + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n";
    }

    public String showTaskList(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.printTasks();
    }

    public String markDoneMessage() {
        return "Nice! I've marked this task as done:\n";
    }

    public String unmarkDoneMessage() {
        return "OK, I've marked this task as not done yet:\n";
    }

    public String deleteTaskMessage(Task task, int taskCount) {
        return  "okay, I've removed this task:\n" + task.toString() +
                "\nNow you have " + taskCount + " tasks in the list.\n";
    }

    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder string = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                string.append((i + 1) + "." + matchingTasks.get(i));
            }
            return string.toString();
        }
    }

    public String showCleared() {
        return "done! no tasks left. what would you like to do today?";
    }
}
