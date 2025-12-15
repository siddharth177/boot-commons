package io.github.siddharth177.bootcommons.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that enables comprehensive logging for a method. When applied, it automatically logs
 * method entry, arguments, exit, and the return value. This is useful for debugging and tracing the
 * flow of an application without cluttering the code with logging statements.
 *
 * <p>The logging is performed at the INFO level. To control the log output, you can configure the
 * logging level for the class where the aspect is defined.</p>
 *
 * <p><b>Usage:</b></p>
 * <pre>
 * {@code
 * @Loggable
 * public String createUser(String username, String password) {
 *     // ... business logic
 *     return "user-created";
 * }
 * }
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {
}
