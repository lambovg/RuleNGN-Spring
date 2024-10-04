package gl.rulengn.family;

import gl.rulengn.annotation.NonBlockingRule;
import gl.rulengn.rule.RuleContainer;
import gl.rulengn.rule.RuleProcessor;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

/**
 * A RuleChain is a collection of Rules that are evaluated in order.
 * If any of the Rules fail, the RuleChain fails.
 */
@Slf4j
public class RuleChain<E, R> implements RuleProcessor<R> {

    private boolean success = false;

    /**
     * Expression POJO.
     */
    @Getter
    private final E expression;

    /**
     * Resulting POJO.
     */
    @Getter
    private final R result;

    private final RuleContainer<E, R> rules;

    public RuleChain(RuleContainer<E, R> rules, @NonNull E expression, @NonNull R result) {
        this.expression = expression;
        this.result = result;
        this.rules = rules;
    }

    public RuleChain(RuleContainer<E, R> rules) {
        this.rules = rules;
        result = null;
        expression = null;
    }

    public RuleChain(RuleContainer<E, R> rules, @NonNull R result) {
        this.rules = rules;
        this.result = result;
        expression = null;
    }

    /**
     * Processes given set rules by given expression.
     *
     * @param expression rule engine expression
     * @return result
     */
    public R process(@NonNull E expression) {
        return doProcessing(expression, result);
    }

    /**
     * Processes given set rules by given expression.
     *
     * @return result
     */
    public R process(@NonNull E expression, @NonNull R result) {
        return doProcessing(expression, result);
    }

    /**
     * Processes given set rules by given expression.
     *
     * @return result
     */
    public R process() {
        return doProcessing(expression, result);
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    /**
     * Does the actual processing of the rule engine based on different inputs.
     *
     * @param expression rule engine expression
     * @param result     rule engine result
     * @return Result
     */
    private R doProcessing(E expression, R result) {
        final String[] ruleName = {""};
        final RuleContainer<?, ?>[] rule = {null};

        boolean b = rules.getRules().stream().allMatch(r -> {
            log.trace("Evaluating RuleChain {}", r);
            ruleName[0] = r.getClass().getName();
            rule[0] = (RuleContainer<?, ?>) r;

            return r.evaluate(expression, result);
        });

        NonBlockingRule annotation = Objects.requireNonNull(rule[0]).getClass().getAnnotation(NonBlockingRule.class);
        // throw exception when failed rule is not NonBlockingRule
        if (!b && !Objects.isNull(annotation)) {
            throw new IllegalArgumentException("Expression does not match all Rules: "
                    + Arrays.stream(ruleName).findFirst());
        }

        if (!b) {
            success = false;
            log.debug("NonBlockingRule failed: {}, further processing of rules in RuleChain are not evaluated", rule[0]);
        } else {
            success = true;
        }

        return result;
    }
}
