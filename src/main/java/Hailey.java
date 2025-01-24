import javafx.concurrent.Task;

import java.util.Scanner;

public class Hailey {
    public static void main(String[] args) throws HaileyException{
        UI ui = new UI();
        TaskList tasks = new TaskList();
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
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5);
                    if (description.equals("")) {
                        throw new EmptyDescriptionException("todo");
                    }
                    ToDo todo = new ToDo(description);
                    tasks.addTask(todo);
                    ui.addTask(todo, tasks);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (HaileyException e) {
                ui.errorMessage();
            }
        }
        scanner.close();
    }
}
