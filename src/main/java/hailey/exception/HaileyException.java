package hailey.exception;

public class HaileyException extends Exception {
    private String message;

    public HaileyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
