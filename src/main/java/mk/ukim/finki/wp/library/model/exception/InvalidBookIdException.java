package mk.ukim.finki.wp.library.model.exception;

public class InvalidBookIdException extends RuntimeException {
    public InvalidBookIdException() {
        super("Book with the provided ID was not found.");
    }
}
