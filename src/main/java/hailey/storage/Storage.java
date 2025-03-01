package hailey.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import hailey.exception.HaileyException;
import hailey.task.Deadline;
import hailey.task.Event;
import hailey.task.Task;
import hailey.task.TaskList;
import hailey.task.ToDo;


/**
 * The Storage class handles loading and saving tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance.
     * @param filePath The path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        checkFileExists();
    }

    /**
     * Ensures that the storage file exists. Creates it if necessary.
     */
    private void checkFileExists() {
        File file = new File(filePath);
        File directory = file.getParentFile();

        try {
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating data file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the storage file.
     * @return A list of tasks loaded from the file.
     * @throws FileNotFoundException If there is an issue loading the file.
     */
    public TaskList readFile() throws FileNotFoundException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                if (type.equals("T")) {
                    task = new ToDo(description);
                } else if (type.equals("D")) {
                    LocalDateTime by = parseDateTime(parts[3]);
                    task = new Deadline(description, by);
                } else if (type.equals("E")) {
                    LocalDateTime start = parseDateTime(parts[3]);
                    LocalDateTime end = parseDateTime(parts[4]);
                    task = new Event(description, start, end);
                } else {
                    throw new HaileyException("Invalid Task Type");
                }

                if (isDone) {
                    task.markAsDone();
                }
                tasks.addTask(task);
            } catch (Exception e) {
                System.out.println("Skipping corrupted line: " + line);
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks to the storage file.
     * @param tasks The list of tasks to save.
     */
    public void writeFile(TaskList tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(tasks.saveTasks());
        } catch (IOException e) {
            System.out.println("Error saving tasks!");
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) throws HaileyException {
        try {
            DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTimeStr, fileFormatter);
        } catch (DateTimeParseException e2) {
            throw new HaileyException("Invalid date/time format. "
                    + "Please use the format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }
}
