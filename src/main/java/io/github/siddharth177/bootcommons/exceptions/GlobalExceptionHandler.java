package io.github.siddharth177.bootcommons.exceptions;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * A global exception handler that provides a centralized mechanism for handling exceptions
 * across the entire application. This handler catches specific exceptions and formats them
 * into a standardized {@link ErrorResponse} object.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link ResourceNotFoundException} and returns a 404 Not Found response.
     *
     * @param ex The caught {@link ResourceNotFoundException}.
     * @return A {@link ResponseEntity} containing the standardized error response.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<@NonNull ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link ErrorResponse} exceptions and returns a custom response based on the
     * details provided in the exception.
     *
     * @param ex The caught {@link ErrorResponse}.
     * @return A {@link ResponseEntity} containing the standardized error response.
     */
    @ExceptionHandler(ErrorResponse.class)
    public ResponseEntity<@NonNull ErrorResponse> handleErrorResponse(ErrorResponse ex) {
        return new ResponseEntity<>(ex, HttpStatus.valueOf(ex.getStatusCode()));
    }

    /**
     * Handles all other unhandled exceptions and returns a 500 Internal Server Error response.
     *
     * @param ex The caught {@link Exception}.
     * @return A {@link ResponseEntity} containing the standardized error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<@NonNull ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
