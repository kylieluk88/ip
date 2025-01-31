import java.io.*;
import java.util.Scanner;

public class Hailey {
    private static final String FILE_PATH = "data/hailey.txt";

    public static void main(String[] args) throws HaileyException, IOException {
        UI ui = new UI();
        File file = new File(FILE_PATH);
        TaskList tasks = new TaskList();
        if (file.exists()) {
            tasks = readFile(file);
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
                    Deadline deadline = new Deadline(parts[0], parts[1]);
                    tasks.addTask(new Deadline(parts[0], parts[1]));
                    ui.addTask(deadline, tasks);
                } else if (input.startsWith("event ")) {
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new HaileyException("oops! event must include '/from' and '/to' keyword");
                    }
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new EmptyDescriptionException("event");
                    }
                    Event event = new Event(parts[0], parts[1], parts[2]);
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
        writeFile(tasks, FILE_PATH);
    }

    private static TaskList readFile(File file) throws FileNotFoundException {
        TaskList tasks = new TaskList();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                String[] parts = line.split(" | ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                if (type.equals("T")) {
                    task = new ToDo(description);
                } else if (type.equals("D")) {
                    task = new Deadline(description, parts[3]);
                } else if (type.equals("E")) {
                    task = new Event(description, parts[3], parts[4]);
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

    private static void writeFile(TaskList tasks, String FILE_PATH) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        writer.write(tasks.printTasks());
    }
}
