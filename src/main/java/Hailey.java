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
            } else {
                ui.addTask(input);
                tasks.addTask(input);
            }
        }
        scanner.close();
    }
}
