package hailey.exception;

public class HaileyException extends Exception {
    public HaileyException(String message) {
        System.out.println("____________________________________________________________\n" +
        message);
    }
}
