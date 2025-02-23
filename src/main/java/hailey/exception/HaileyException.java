package hailey.exception;

/**
 * The exceptions used by hailey bot.
 */
public class HaileyException extends Exception {
    private String message;

    public HaileyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
