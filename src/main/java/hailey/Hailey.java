package hailey;

import hailey.parser.Parser;
import hailey.storage.Storage;
import hailey.task.TaskList;
import hailey.ui.Ui;
import hailey.exception.HaileyException;
import java.io.*;
import java.util.Scanner;

/**
 * The main class of the Hailey chatbot application.
 * Initializes components and runs the chatbot.
 */
public class Hailey {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a HaileyBot instance.
     * @param filePath The path to the storage file.
     */
    public Hailey(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            tasks = storage.readFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts the chatbot and processes user commands.
     */
    public void run() throws IOException {
        tasks = storage.readFile();
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

    /**
     * The main entry point of the program.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) throws IOException {
        new Hailey("data/tasks.txt").run();
    }
}
