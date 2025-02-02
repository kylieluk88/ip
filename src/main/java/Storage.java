import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        checkFileExists();
    }

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

    public void writeFile(TaskList tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(tasks.saveTasks());
        } catch (IOException e) {
            System.out.println("Error saving tasks!");
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) throws HaileyException {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, inputFormatter);
        } catch (DateTimeParseException e1) {
            try {
                DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                return LocalDateTime.parse(dateTimeStr, fileFormatter);
            } catch (DateTimeParseException e2) {
                throw new HaileyException("Invalid date/time format. Please use the format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
            }
        }
    }
}
