package engine.exceptions;

public class UserWithEmailAlreadyExists extends RuntimeException {
    public UserWithEmailAlreadyExists(String message) {
        super(message);
    }
}
