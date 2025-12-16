package io.github.siddharth177.bootcommons.aop.annotations;

import io.github.siddharth177.bootcommons.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that provides centralized exception handling for a method. When a method
 * annotated with {@code @HandleException} throws an exception, it is caught and wrapped
 * in a custom exception containing a standardized {@link ErrorResponse}.
 *
 * <p>This allows for a consistent error response format across the application, and the
 * status code and message can be customized directly in the annotation.</p>
 *
 * <p><b>Usage:</b></p>
 * <pre>
 * {@code
 * @HandleException(statusCode = HttpStatus.BAD_REQUEST, message = "Failed to process the request.")
 * public void processRequest() throws Exception {
 *     // ... logic that might throw an exception
 * }
 * }
 * </pre>
 *
 * @see #statusCode()
 * @see #message()
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleException {

    /**
     * The HTTP status code to be included in the error response.
     *
     * @return The HTTP status code.
     */
    HttpStatus statusCode() default HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * A descriptive message to be included in the error response.
     *
     * @return The error message.
     */
    String message() default "An unexpected error occurred.";
}
