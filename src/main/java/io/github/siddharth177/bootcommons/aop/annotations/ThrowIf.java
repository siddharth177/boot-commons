package io.github.siddharth177.bootcommons.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that conditionally throws a specified exception after a method executes,
 * based on the evaluation of a SpEL (Spring Expression Language) expression. The expression
 * is evaluated against the method's return value.
 *
 * <p>This is useful for validating return values and ensuring that certain post-conditions
 * are met without adding boilerplate `if-throw` statements inside the method body.</p>
 *
 * <p><b>Usage:</b></p>
 * <pre>
 * {@code
 * // Throws an IllegalStateException if the returned list is empty.
 * @ThrowIf(expression = "#returnValue.isEmpty()",
 *          exception = IllegalStateException.class,
 *          message = "The returned list cannot be empty.")
 * public List<String> getActiveUsers() {
 *     // ... business logic
 *     return new ArrayList<>();
 * }
 * }
 * </pre>
 *
 * @see #expression()
 * @see #exception()
 * @see #message()
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThrowIf {

    /**
     * The SpEL expression to evaluate against the method's return value. The return value
     * can be accessed in the expression using the name {@code #returnValue}.
     *
     * @return The SpEL expression.
     */
    String expression();

    /**
     * The type of exception to throw if the SpEL expression evaluates to {@code true}.
     * The exception class must have a constructor that accepts a single {@link String} message.
     *
     * @return The exception class.
     */
    Class<? extends Throwable> exception();

    /**
     * The message to be passed to the exception's constructor.
     *
     * @return The exception message.
     */
    String message() default "Condition evaluated to true, throwing exception.";
}
