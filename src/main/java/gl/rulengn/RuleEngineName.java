package gl.rulengn;

import java.lang.annotation.*;

/**
 * Mark a method as entry point for a RuleEngine.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface RuleEngineName {
    /**
     * Name of the RuleEngine to use. Default option is not mandatory.
     */
    Class<?> value() default Object.class;

    /**
     * RuleEngine expression.
     */
    Class<?> expression() default Object.class;

    /**
     * RuleEngine result.
     */
    Class<?> result() default Object.class;

    /**
     * Overrides the default rule engine result handler.
     */
    Class<?> handler() default Object.class;

    /**
     * RuleEngine qualifier used in spring-di.
     */
    String qualifier() default "";
}

