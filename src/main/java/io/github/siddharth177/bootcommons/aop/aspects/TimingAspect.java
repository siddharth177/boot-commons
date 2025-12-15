package io.github.siddharth177.bootcommons.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * An aspect that provides timing functionality for methods annotated with
 * {@link io.github.siddharth177.bootcommons.aop.annotations.Timed}.
 *
 * <p>This aspect uses {@code @Around} advice to wrap the execution of annotated methods,
 * allowing it to log the execution time.</p>
 */
@Aspect
@Component
public class TimingAspect {

    private static final Logger logger = LoggerFactory.getLogger(TimingAspect.class);

    /**
     * An around advice that logs the execution time of a method annotated with {@code @Timed}.
     *
     * @param joinPoint The proceeding join point.
     * @return The result of the method execution.
     * @throws Throwable If an error occurs during method execution.
     */
    @Around("@annotation(io.github.siddharth177.bootcommons.aop.annotations.Timed)")
    public Object timed(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        logger.info("Method {} executed in {} ms", methodName, (endTime - startTime));

        return result;
    }
}
