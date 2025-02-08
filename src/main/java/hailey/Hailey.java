package hailey;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import hailey.exception.HaileyException;
import hailey.parser.Parser;
import hailey.storage.Storage;
import hailey.task.TaskList;
import hailey.ui.Ui;

/**
 * The main class of the Hailey chatbot application.
 * Initializes components and runs the chatbot.
 */
public class Hailey {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private String commandType = "test";


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
                isRunning = !input.equals("bye");
                parser.processCommand(input, tasks, ui, storage);
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

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return parser.processCommand(input, tasks, ui, storage);
        } catch (HaileyException e) {
            return "Error: " + e.getMessage();
        }
    }
    public String getCommandType() {
        return commandType;
    }


}
