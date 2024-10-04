package gl.rulengn.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface RuleEnginePayload {
    Class<?> value() default Object.class;
}
