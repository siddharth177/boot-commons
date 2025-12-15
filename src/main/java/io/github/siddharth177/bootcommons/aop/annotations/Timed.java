package io.github.siddharth177.bootcommons.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that, when applied to a method, logs the execution time of that method.
 * This is particularly useful for performance monitoring and identifying bottlenecks in
 * an application. The execution time is logged in milliseconds at the INFO level.
 *
 * <p><b>Usage:</b></p>
 * <pre>
 * {@code
 * @Timed
 * public void processData() {
 *     // ... time-consuming operation
 * }
 * }
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {
}
