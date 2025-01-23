import java.util.Scanner;

public class Hailey {
    static String line = "____________________________________________________________\n";
    public static void main(String[] args) {
        System.out.println(line + "Hello! I'm Hailey :)\n"
            + "What can I do for you?\n" + line);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                exit();
                break;
            } else {
                System.out.println(input +"\n" + line);
            }
        }
        scanner.close();
    }

    static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
