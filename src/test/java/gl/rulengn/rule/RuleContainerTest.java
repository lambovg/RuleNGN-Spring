package gl.rulengn.rule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RuleContainerTest {

    @Test
    void appendRule() {
        RuleContainer<String, Integer> container = new RuleContainer<>();
        Rule<String, Integer> rule = new Rule<>() {
            @Override
            public Integer getResult(Integer result) {
                return 0;
            }

            @Override
            public boolean evaluate(String expression, Integer result) {
                return false;
            }
        };
        container.appendRule(rule);
        assertTrue(container.getRules().contains(rule));
    }

    @Test
    void appendRuleWithNull() {
        RuleContainer<String, Integer> container = new RuleContainer<>();
        Rule<String, Integer> rule = null;
        container.appendRule(rule);
        assertNotNull(container.getRules());
    }
}
