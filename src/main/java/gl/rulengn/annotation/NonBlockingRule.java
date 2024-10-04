package gl.rulengn.annotation;

import java.lang.annotation.*;

/**
 * Indicates that a rule is not throwing an exception when it fails to evaluate.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NonBlockingRule {
}
