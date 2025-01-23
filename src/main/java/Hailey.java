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
            } else {
                ui.addTask(input);
                tasks.addTask(input);
            }
        }
        scanner.close();
    }
}
