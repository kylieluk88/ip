import java.io.*;
import java.util.Scanner;


public class Hailey {
    private static final String FILE_PATH = "data/hailey.txt";
    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private Parser parser;

    public Hailey(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            tasks = storage.readFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
        if (file.exists()) {
            tasks = storage.readFile();
        } else {
            file.createNewFile();
        }
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            try {
                isRunning = parser.processCommand(input, tasks, ui, storage);
            } catch (HaileyException e) {
                ui.errorMessage();
            }
        }
        scanner.close();
        storage.writeFile(tasks);
    }

    public static void main(String[] args) throws IOException {
        new Hailey("data/tasks.txt").run();
    }
}
