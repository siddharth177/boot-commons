package io.github.siddharth177.bootcommons.aop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Configuration class to enable Aspect-Oriented Programming (AOP) support in the application.
 *
 * <p>The {@code @EnableAspectJAutoProxy} annotation is essential for Spring to recognize and
 * process the aspects defined in the application, such as those for logging, timing, and
 * exception handling. This enables the aspects to intercept method executions and apply
 * the defined advice.</p>
 *
 * <p>By including this configuration, any beans in the application context that are advised
 * by an aspect will be proxied, and the corresponding advice will be executed as configured.</p>
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    /**
     * Default constructor for {@code AopConfig}.
     */
    public AopConfig() {
        // Default constructor
    }
}
