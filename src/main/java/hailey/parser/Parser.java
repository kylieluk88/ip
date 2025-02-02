package hailey.parser;

import hailey.task.*;
import hailey.storage.Storage;
import hailey.ui.Ui;
import hailey.exception.HaileyException;
import hailey.exception.EmptyDescriptionException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
    public boolean processCommand(String input, TaskList tasks, Ui ui, Storage storage) throws HaileyException {
        if (input.equals("bye")) {
            ui.sayBye();
            return false;
        } else if (input.equals("help")) {
            ui.help();
        } else if (input.equals("list")) {
            ui.showTaskList(tasks);
        } else if (input.startsWith("mark ")) {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            ui.markDone();
            tasks.markDone(taskNumber);
        } else if (input.startsWith("unmark ")) {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            ui.unmarkDone();
            tasks.unmarkDone(taskNumber);
        } else if (input.startsWith("delete ")) {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskNumber <0 || taskNumber >= tasks.getCount()) {
                throw new HaileyException("Invalid task number! Please choose a task number within your list.\n" +
                        "You currently have " + tasks.getCount() + " tasks.");
            }
            ui.deleteTask(tasks, taskNumber);
            tasks.deleteTask(taskNumber);
        } else if (input.startsWith("deadline")) {
            if (!input.contains("/by")) {
                throw new HaileyException("oops! deadline must include '/by' keyword");
            }
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new EmptyDescriptionException("deadline");
            }
            LocalDateTime by = parseDateTime(parts[1]);
            Deadline deadline = new Deadline(parts[0], by);
            tasks.addTask(new Deadline(parts[0], by));
            ui.addTask(deadline, tasks);
        } else if (input.startsWith("event ")) {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new HaileyException("oops! event must include '/from' and '/to' keyword");
            }
            String[] parts = input.substring(6).split(" /from | /to ");
            if (parts.length < 3) {
                throw new EmptyDescriptionException("event");
            }
            LocalDateTime start = parseDateTime(parts[1]);
            LocalDateTime end = parseDateTime(parts[2]);
            Event event = new Event(parts[0], start, end);
            tasks.addTask(event);
            ui.addTask(event, tasks);
        } else if (input.startsWith("todo")) {
            if (input.substring(4).isEmpty()) {
                throw new EmptyDescriptionException("todo");
            }
            String description = input.substring(5);
            ToDo todo = new ToDo(description);
            tasks.addTask(todo);
            ui.addTask(todo, tasks);
        } else if (input.startsWith("find ")) {
            String keyword = input.substring(5).trim();
            ArrayList<Task> matchingTasks = tasks.find(keyword);
            ui.showMatchingTasks(matchingTasks);
        } else {
            throw new HaileyException("Sorry, didn't quite catch that. What did you say?");
        }
        return true;
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) throws HaileyException {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, inputFormatter);
        } catch (DateTimeParseException e1) {
            throw new HaileyException("Invalid date/time format. Please use the format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }
}
