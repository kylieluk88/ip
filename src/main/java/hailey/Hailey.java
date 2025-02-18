package hailey;

import java.io.FileNotFoundException;

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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String response = parser.processCommand(input, tasks, ui, storage);
            storage.writeFile(tasks);
            return response;
        } catch (HaileyException e) {
            return e.getMessage();
        }
    }
}
