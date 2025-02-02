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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveFormat() {
        return "T" + super.saveFormat();
    }
}
