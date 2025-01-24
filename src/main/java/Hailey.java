import javafx.concurrent.Task;

import java.util.Scanner;

public class Hailey {
    public static void main(String[] args) {
        UI ui = new UI();
        TaskList tasks = new TaskList();
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                ui.sayBye();
                break;
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
            } else if (input.startsWith("deadline ")) {
                ui.addTask();
                String[] parts = input.substring(9).split(" /by ");
                tasks.addTask(new Deadline(parts[0], parts[1]));
            } else if (input.startsWith("event ")) {
                ui.addTask();
                String[] parts = input.substring(6).split(" /from | /to ");
                tasks.addTask(new Event(parts[0], parts[1], parts[2]));
            } else if (input.startsWith("todo ")) {
                ui.addTask();
                String description = input.substring(5);
                tasks.addTask(new ToDo(description));
            }
        }
        scanner.close();
    }
}
