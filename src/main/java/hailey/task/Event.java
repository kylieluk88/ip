package hailey.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs within a specific time range.
 */
public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event task.
     * @param description The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the task.
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[E]" + super.toString() + " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }

    /**
     * Returns a formatted string for saving the Event task to a file.
     * @return The formatted save string.
     */
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "E" + super.toSaveFormat() + " | " + start.format(formatter) + " | " + end.format(formatter);
    }
}

