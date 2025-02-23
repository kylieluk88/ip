package hailey.ui;

import java.util.ArrayList;

import hailey.task.Task;
import hailey.task.TaskList;

/**
 * Represents the User Interface of the chatbot.
 *
 */
public class Ui {
    /**
     * Returns the greeting message when the chatbot starts.
     *
     * @return A greeting message.
     */
    public String greet() {
        return "hi there! i'm hailey :)\n"
                + "what would you like to do today?\n";
    }

    /**
     * Returns a help message that displays available commands.
     *
     * @return A formatted help message listing available commands.
     */
    public String showHelp() {
        return "you can add tasks by:\n"
                + " > todo [description]\n"
                + " > deadline [description] /by [deadline]\n"
                + " > event [description] /from [start] /to [end]\n"
                + "* date format: d/M/yyyy HHmm\n"
                + "\nother commands:\n" + " > list //to view your list of tasks\n"
                + " > mark [task number] //to mark a task as done\n"
                + " > unmark [task number] //to unmark a task as done"
                + " > delete [task number] //to delete a task from your list\n"
                + " > find [keyword] //returns a list of tasks containing this keyword\n"
                + " > clear //clears all tasks from list\n"
                + " > bye //exits program";
    }

    /**
     * Returns a farewell message when the chatbot exits.
     *
     * @return A goodbye message.
     */
    public String sayBye() {
        return "aw okay bye.. good luck with your tasks ;D\n";
    }

    /**
     * Returns a confirmation message when a new task is added.
     *
     * @param task The task that was added.
     * @param tasks The current task list.
     * @return A message confirming task addition.
     */
    public String printAddMessage(Task task, TaskList tasks) {
        return "got it! i've added this task:\n  " + task.toString()
                + "\nnow you have " + tasks.getSize() + " tasks in the list.\n";
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     *
     * @param tasks The task list to be displayed.
     * @return A formatted string of tasks.
     */
    public String showTaskList(TaskList tasks) {
        return "here are the tasks in your list:\n" + tasks.printTasks();
    }

    /**
     * Returns a message confirming that a task has been marked as done.
     *
     * @return A message confirming task completion.
     */
    public String markDoneMessage() {
        return "good job! i've marked this task as done:\n";
    }

    /**
     * Returns a message confirming that a task has been unmarked as done.
     *
     * @return A message confirming task unmarking.
     */
    public String unmarkDoneMessage() {
        return "okay, i've marked this task as not done yet:\n";
    }

    /**
     * Returns a confirmation message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The remaining number of tasks in the list.
     * @return A message confirming task deletion.
     */
    public String deleteTaskMessage(Task task, int taskCount) {
        return "okay, i've removed this task:\n" + task.toString()
                + "\nnow you have " + taskCount + " tasks in the list.\n";
    }

    /**
     * Returns a list of tasks that match a given keyword.
     *
     * @param matchingTasks The list of tasks matching the keyword.
     * @return A formatted message displaying matching tasks.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "no matching tasks found";
        } else {
            StringBuilder string = new StringBuilder("here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                string.append((i + 1) + ". " + matchingTasks.get(i) + "\n");
            }
            return string.toString();
        }
    }

    /**
     * Returns a message confirming that all tasks have been cleared.
     *
     * @return A message indicating that the task list is empty.
     */
    public String showCleared() {
        return "done! no tasks left. what would you like to do today?";
    }
}
