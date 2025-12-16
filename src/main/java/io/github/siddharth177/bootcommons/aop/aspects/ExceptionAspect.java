package io.github.siddharth177.bootcommons.aop.aspects;

import io.github.siddharth177.bootcommons.aop.annotations.HandleException;
import io.github.siddharth177.bootcommons.aop.annotations.ThrowIf;
import io.github.siddharth177.bootcommons.exceptions.ErrorResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

/**
 * An aspect that provides advanced exception handling capabilities. It is responsible for
 * processing methods annotated with {@link HandleException} and {@link ThrowIf}.
 *
 * <p>This aspect includes advice for:
 * <ul>
 *     <li><b>Exception Handling:</b> Catches exceptions from methods annotated with {@code @HandleException},
 *     wraps them in a {@link ErrorResponse}, and re-throws them.</li>
 *     <li><b>Conditional Exception Throwing:</b> Conditionally throws exceptions based on a SpEL expression
 *     evaluated against the return value of methods annotated with {@code @ThrowIf}.</li>
 * </ul>
 */
@Aspect
@Component
public class ExceptionAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);
    private final ExpressionParser parser = new SpelExpressionParser();

    /**
     * An after-throwing advice that catches exceptions from methods annotated with {@code @HandleException},
     * wraps them in a {@link ErrorResponse}, and re-throws them.
     *
     * @param joinPoint       The join point.
     * @param handleException The {@code @HandleException} annotation instance.
     * @param ex              The exception thrown by the method.
     */
    @AfterThrowing(pointcut = "@annotation(handleException)", throwing = "ex")
    public void handleException(JoinPoint joinPoint, HandleException handleException, Throwable ex) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.error("Exception in method: {}. Details: {}", methodName, ex.getMessage(), ex);

        throw new ErrorResponse(handleException.statusCode(), handleException.message(), ex);
    }

    /**
     * An after-returning advice that conditionally throws an exception based on a SpEL expression
     * for methods annotated with {@code @ThrowIf}.
     *
     * @param joinPoint    The join point.
     * @param throwIf      The {@code @ThrowIf} annotation.
     * @param returnValue The value returned by the method.
     * @throws Throwable If the SpEL expression evaluates to true.
     */
    @AfterReturning(pointcut = "@annotation(throwIf)", returning = "returnValue")
    public void throwIf(JoinPoint joinPoint, ThrowIf throwIf, Object returnValue) throws Throwable {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("returnValue", returnValue);

        boolean shouldThrow = Boolean.TRUE.equals(parser.parseExpression(throwIf.expression()).getValue(context, Boolean.class));

        if (shouldThrow) {
            Class<? extends Throwable> exceptionClass = throwIf.exception();
            String message = throwIf.message();
            try {
                Constructor<? extends Throwable> constructor = exceptionClass.getConstructor(String.class);
                throw constructor.newInstance(message);
            } catch (Exception e) {
                logger.error("Failed to instantiate exception of type {}", exceptionClass.getName(), e);
                // Fallback to a generic exception if instantiation fails
                throw new RuntimeException("Condition met to throw exception, but failed to create the specified exception: " + message);
            }
        }
    }
}
