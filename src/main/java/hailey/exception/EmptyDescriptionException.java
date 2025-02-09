package hailey.exception;

public class EmptyDescriptionException extends HaileyException {
    public EmptyDescriptionException(String command) {
        super("oops! the fields of a " + command + " cannot be empty.");
    }
}
