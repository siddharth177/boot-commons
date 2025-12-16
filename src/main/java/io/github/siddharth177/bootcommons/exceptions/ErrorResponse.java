package io.github.siddharth177.bootcommons.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * A standardized DTO for representing error responses in the application.
 * This class also serves as a RuntimeException to be thrown by aspects
 * and handled by the GlobalExceptionHandler.
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"cause", "stackTrace", "localizedMessage", "suppressed"})
public class ErrorResponse extends RuntimeException {

    /**
     * The HTTP status code of the error.
     */
    private final int statusCode;

    /**
     * The descriptive message explaining the error.
     */
    private final String message;

    /**
     * The timestamp when the error occurred.
     */
    private final LocalDateTime timestamp;

    /**
     * Constructs a new {@code ErrorResponse} with the given status, message, and cause.
     *
     * @param status  The HTTP status of the error.
     * @param message The descriptive message explaining the error.
     * @param cause   The cause of the error.
     */
    public ErrorResponse(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = status.value();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs a new {@code ErrorResponse} with the given status and message.
     *
     * @param status  The HTTP status of the error.
     * @param message The descriptive message explaining the error.
     */
    public ErrorResponse(HttpStatus status, String message) {
        super(message);
        this.statusCode = status.value();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
