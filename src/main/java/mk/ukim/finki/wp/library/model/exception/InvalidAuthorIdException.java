package mk.ukim.finki.wp.library.model.exception;

public class InvalidAuthorIdException extends RuntimeException {
    public InvalidAuthorIdException() {
      super("Author with the provided ID was not found.");

    }
}
