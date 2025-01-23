import java.util.Scanner;

public class Hailey {
    public static void main(String[] args) {
        UI ui = new UI();
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                ui.sayBye();
                break;
            } else {
                ui.addTask(input);
            }
        }
        scanner.close();
    }
}
