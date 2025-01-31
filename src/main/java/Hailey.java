import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Hailey {
    private static final String FILE_PATH = "data/hailey.txt";

    public static void main(String[] args) throws HaileyException, IOException {
        UI ui = new UI();
        TaskList tasks = new TaskList();
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
        if (file.exists()) {
            tasks = readFile(file);
        } else {
            file.createNewFile();
        }
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    ui.sayBye();
                    break;
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
                } else {
                    throw new HaileyException("Sorry, didn't quite catch that. What did you say?");
                }
            } catch (HaileyException e) {
                ui.errorMessage();
            }
        }
        scanner.close();
        writeFile(tasks, file);
    }

    private static TaskList readFile(File file) throws FileNotFoundException {
        TaskList tasks = new TaskList();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                String[] parts = line.split(" \\| ");
                System.out.println(Arrays.stream(parts).toList());
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

    private static void writeFile(TaskList tasks, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(tasks.saveTasks());
        writer.close();
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
