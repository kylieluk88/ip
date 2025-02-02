package hailey.parser;

import hailey.exception.HaileyException;
import hailey.storage.Storage;
import hailey.task.TaskList;
import hailey.task.ToDo;
import hailey.ui.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Parser parser;
    private TaskList taskList;
    private UI ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        parser = new Parser();
        taskList = new TaskList();
        ui = new UI();
        storage = new Storage("data/test_hailey.txt"); // Test file
    }

    @Test
    void processCommand_validTodoCommand_taskAdded() throws HaileyException {
        parser.processCommand("todo Buy milk", taskList, ui, storage);
        assertEquals(1, taskList.getCount());
        assertEquals("[T][ ] Buy milk", taskList.getTask(0).toString());
    }

    @Test
    void processCommand_invalidCommand_throwsException() {
        assertThrows(HaileyException.class, () -> {
            parser.processCommand("invalidCommand", taskList, ui, storage);
        });
    }
}
