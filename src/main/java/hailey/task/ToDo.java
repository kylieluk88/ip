package hailey.task;
/**
 * Represents a simple task without a deadline or time range.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task.
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string for saving the Event task to a file.
     * @return The formatted save string.
     */
    public String saveFormat() {
        return "T" + super.saveFormat();
    }
}
