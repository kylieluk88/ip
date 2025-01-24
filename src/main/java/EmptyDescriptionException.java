public class EmptyDescriptionException extends HaileyException {
    public EmptyDescriptionException(String command) {
        super("OOPS!!! The fields of a " + command + " cannot be empty.");
    }
}
