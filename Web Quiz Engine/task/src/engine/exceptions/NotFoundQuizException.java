package engine.exceptions;

public class NotFoundQuizException extends RuntimeException {
    public NotFoundQuizException(String message) {
        super(message);
    }
}
