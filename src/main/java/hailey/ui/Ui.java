package hailey.ui;

import hailey.task.Task;
import hailey.task.TaskList;

import java.util.ArrayList;

public class Ui {
    public String greet() {
        return  "hi there! i'm hailey :)\n"
                + "what would you like to do today?\n";
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
                " > unmark [task number] //to unmark a task as done" +
                " > delete [task number] //to delete a task from your list\n" +
                " > find [keyword] //returns a list of tasks containing this keyword\n" +
                " > clear //clears all tasks from list\n" +
                " > bye //exits program";
    }

    public String sayBye() {
        return "aw okay bye.. good luck with your tasks ;D\n";
    }

    public String printAddMessage(Task task, TaskList tasks) {
        return "got it! i've added this task:\n  " + task.toString()
                + "\nnow you have " + tasks.getSize() + " tasks in the list.\n";
    }

    public String showTaskList(TaskList tasks) {
        return "here are the tasks in your list:\n" + tasks.printTasks();
    }

    public String markDoneMessage() {
        return "good job! I've marked this task as done:\n";
    }

    public String unmarkDoneMessage() {
        return "okay, I've marked this task as not done yet:\n";
    }

    public String deleteTaskMessage(Task task, int taskCount) {
        return  "okay, I've removed this task:\n" + task.toString() +
                "\nnow you have " + taskCount + " tasks in the list.\n";
    }

    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "no matching tasks found";
        } else {
            StringBuilder string = new StringBuilder("here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                string.append((i + 1) + ". " + matchingTasks.get(i) +"\n");
            }
            return string.toString();
        }
    }

    public String showCleared() {
        return "done! no tasks left. what would you like to do today?";
    }
}
