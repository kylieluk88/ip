package hailey.exception;

/**
 * The exception thrown when the field of a command is empty.
 */
public class EmptyDescriptionException extends HaileyException {
    public EmptyDescriptionException(String command) {
        super("oops! the fields of a " + command + " cannot be empty.");
    }
}
