package hailey.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hailey.exception.HaileyException;


public class TaskListTest {

    @Test
    void addTask_validTask_taskIsAdded() throws HaileyException {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("Read a book");
        taskList.addTask(todo);
        assertEquals(1, taskList.getSize());
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    void markDone_validIndex_taskIsMarked() throws HaileyException {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("Read a book");
        taskList.addTask(todo);
        taskList.markDone(0);
        assertTrue(taskList.getTask(0).isDone);
    }

    @Test
    void deleteTask_validIndex_taskIsDeleted() throws HaileyException {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("Read a book");
        taskList.addTask(todo);
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }
}

