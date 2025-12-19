package io.github.siddharth177.bootcommons.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * An aspect that provides logging functionality for methods annotated with
 * {@link io.github.siddharth177.bootcommons.aop.annotations.Loggable}.
 *
 * <p>This aspect uses {@code @Around} advice to wrap the execution of annotated methods,
 * allowing it to log method entry, exit, arguments, and return values.</p>
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Around advice that logs the entry, exit, and execution time of a method
     * annotated with {@code @Loggable}.
     *
     * @param joinPoint The proceeding join point.
     * @return The result of the method execution.
     * @throws Throwable If an error occurs during method execution.
     */
    @Around("@annotation(io.github.siddharth177.bootcommons.aop.annotations.Loggable)")
    public Object loggable(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] methodArgs = joinPoint.getArgs();

        logger.info("Entering method: {} with arguments: {}", methodName, Arrays.toString(methodArgs));

        Object result = joinPoint.proceed();

        logger.info("Exiting method: {} with result: {}", methodName, result);

        return result;
    }
}
