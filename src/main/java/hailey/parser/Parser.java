package hailey.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import hailey.exception.EmptyDescriptionException;
import hailey.exception.HaileyException;
import hailey.storage.Storage;
import hailey.task.Deadline;
import hailey.task.Event;
import hailey.task.Task;
import hailey.task.TaskList;
import hailey.task.ToDo;
import hailey.ui.Ui;


/**
 * The Parser class interprets user input and executes the corresponding commands.
 */
public class Parser {

    /**
     * Processes the user command and performs the appropriate actions.
     * @param input The user's input command.
     * @param tasks The task list that stores all tasks.
     * @param ui The user interface handler.
     * @param storage The storage handler for reading/writing data.
     * @return A boolean indicating whether the program should continue running.
     * @throws HaileyException If an invalid command is encountered.
     */
    public String processCommand(String input, TaskList tasks, Ui ui, Storage storage) throws HaileyException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case "hi":
            return ui.greet();
        case "bye":
            return ui.sayBye();
        case "help":
            return ui.showHelp();
        case "list":
            return ui.showTaskList(tasks);
        case "clear":
            tasks.clearTasks();
            return ui.showCleared();
        case "mark":
        case "unmark":
        case "delete":
            return handleTaskModification(command, input, tasks, ui);
        case "deadline":
            return handleDeadline(input, tasks, ui);
        case "event":
            return handleEvent(input, tasks, ui);
        case "todo":
            return handleToDo(input, tasks, ui);
        case "find":
            return handleFind(input, tasks, ui);
        default:
            throw new HaileyException("sorry, what did you say? type 'help' to see a list of command formats :)");
        }
    }

    /**
     * Handles marking, unmarking, and deleting tasks.
     */
    private String handleTaskModification(String command, String input, TaskList tasks, Ui ui) throws HaileyException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.getSize()) {
            throw new HaileyException("invalid task number! please choose a task number within your list.\n"
                    + "you currently have " + tasks.getSize() + " tasks.");
        }

        switch (command) {
        case "mark":
            return ui.markDoneMessage() + tasks.markDone(taskNumber);
        case "unmark":
            return ui.unmarkDoneMessage() + tasks.unmarkDone(taskNumber);
        case "delete":
            Task deletedTask = tasks.getTask(taskNumber);
            tasks.deleteTask(taskNumber);
            return ui.deleteTaskMessage(deletedTask, tasks.getSize());
        default:
            return "";
        }
    }

    /**
     * Handles adding a deadline task.
     */
    private String handleDeadline(String input, TaskList tasks, Ui ui) throws HaileyException {
        if (!input.contains("/by")) {
            throw new HaileyException("oops! deadline must include '/by' keyword");
        }
        String[] deadlineParts = input.substring(9).split(" /by ");
        if (deadlineParts.length < 2) {
            throw new EmptyDescriptionException("deadline");
        }
        LocalDateTime by = parseDateTime(deadlineParts[1]);
        Deadline deadline = new Deadline(deadlineParts[0], by);
        tasks.addTask(deadline);
        return ui.printAddMessage(deadline, tasks);
    }

    /**
     * Handles adding an event task.
     */
    private String handleEvent(String input, TaskList tasks, Ui ui) throws HaileyException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new HaileyException("oops! event must include '/from' and '/to' keyword");
        }
        String[] eventParts = input.substring(6).split(" /from | /to ");
        if (eventParts.length < 3) {
            throw new EmptyDescriptionException("event");
        }
        LocalDateTime start = parseDateTime(eventParts[1]);
        LocalDateTime end = parseDateTime(eventParts[2]);
        Event event = new Event(eventParts[0], start, end);
        tasks.addTask(event);
        return ui.printAddMessage(event, tasks);
    }

    /**
     * Handles adding a ToDo task.
     */
    private String handleToDo(String input, TaskList tasks, Ui ui) throws HaileyException {
        if (input.substring(4).isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        String description = input.substring(5);
        ToDo todo = new ToDo(description);
        tasks.addTask(todo);
        return ui.printAddMessage(todo, tasks);
    }

    /**
     * Handles finding tasks that match a keyword.
     */
    private String handleFind(String input, TaskList tasks, Ui ui) {
        String keyword = input.substring(5).trim();
        ArrayList<Task> matchingTasks = tasks.find(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Processes the date and time string into LocalDateTime format to be stored.
     * @param dateTimeStr date and time string to be parsed.
     * @throws HaileyException If an invalid date format is encountered.
     */
    private static LocalDateTime parseDateTime(String dateTimeStr) throws HaileyException {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, inputFormatter);
        } catch (DateTimeParseException e1) {
            throw new HaileyException("invalid date/time format. "
                    + "please use the format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }
}
