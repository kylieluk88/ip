package hailey.task;
import hailey.exception.HaileyException;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * @return "[X]" if done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * @return The formatted task string.
     */
    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Returns a string formatted for saving to a file.
     * @return The formatted string for saving.
     */
    public String toSaveFormat() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public String getDescription() {
        return this.description;
    }
}
