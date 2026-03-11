package mk.ukim.finki.wp.library.model.exception;

public class InvalidCountryIdException extends RuntimeException {
    public InvalidCountryIdException() {
        super("Country with the provided ID was not found.");
    }
}
