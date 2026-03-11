package mk.ukim.finki.wp.library.web.handler;

import mk.ukim.finki.wp.library.model.exception.InvalidBookIdException;
import mk.ukim.finki.wp.library.web.controller.BookController;
import mk.ukim.finki.wp.library.web.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = BookController.class)
public class BookControllerHandler {
    @ExceptionHandler(InvalidBookIdException.class)
    public ResponseEntity<ApiError> handleNotFound(InvalidBookIdException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

}
