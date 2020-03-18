package Exceptions;

public class BusinessException extends RuntimeException {

    String message;

    public BusinessException(String message) {
        super(message);
    }
}
