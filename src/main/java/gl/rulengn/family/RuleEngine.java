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
 * A RuleEngine is a collection of Rules that are evaluated in order.
 * If any of the Rules succeed, the RuleChain exits and get the first successful rul.
 *
 * @param <E>
 * @param <R>
 */
@Slf4j
public class RuleEngine<E, R> implements RuleProcessor<R> {

    private boolean success = false;

    /**
     * Expression POJO.
     */
    @Getter
    private E expression;

    /**
     * Resulting POJO.
     */
    @Getter
    private R result;

    private final RuleContainer<E, R> rules;

    public RuleEngine(RuleContainer<E, R> rules, @NonNull E expression, @NonNull R result) {
        this.expression = expression;
        this.result = result;
        this.rules = rules;
    }

    public RuleEngine(RuleContainer<E, R> rules) {
        this.rules = rules;
    }

    /**
     * Processes given set rules by given expression.
     *
     * @param expression rule engine expression
     * @param result     rule engine result
     * @return Rule
     */
    public R process(@NonNull E expression, @NonNull R result) {
        return doProcessing(expression, result);
    }

    /**
     * @return Rule
     */
    public R process() {
        return doProcessing(this.expression, this.result);
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    /**
     * @return Rule
     */
    private R doProcessing(E expression, R result) {
        final String[] ruleName = {""};
        final RuleContainer<?, ?>[] ruleObject = {null};

        try {
            rules.getRules()
                    .stream()
                    .filter(r -> {
                        log.debug("Evaluating RuleEngine {}", r);

                        ruleName[0] = r.getClass().getName();
                        ruleObject[0] = (RuleContainer<?, ?>) r;
                        return r.evaluate(expression, result);
                    })
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Expression does not match any Rule: "
                            + Arrays.stream(ruleName).findFirst()));
            success = true;
        } catch (IllegalArgumentException e) {
            success = false;

            NonBlockingRule annotation = Objects.requireNonNull(ruleObject[0]).getClass()
                    .getAnnotation(NonBlockingRule.class);

            // throw exception when failed rule is not NonBlockingRule
            if (Objects.isNull(annotation)) {
                throw e;
            }

            log.trace(e.getMessage());
            log.debug("NonBlockingRule failed: {}, further processing of rules in RuleEngine are not evaluated",
                    ruleObject[0]);
        }

        return result;
    }
}
