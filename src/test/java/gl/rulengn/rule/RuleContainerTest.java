package gl.rulengn.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleContainerTest {

    @Test
    void givenRule_whenAppendRule_thenRuleExistsInRuleContainer() {
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
        assertTrue(container.appendRule(rule).getRules().contains(rule));
    }

    @Test
    void givenNullRule_whenAppendRule_thenThrowsNullPointerException() {
        RuleContainer<String, Integer> container = new RuleContainer<>();
        assertThrows(NullPointerException.class, () -> container.appendRule(null));
    }

    @Test
    void givenExistingRule_WhenReplaceRule_ThenOnlyNewRuleExists() {
        RuleContainer<String, Integer> container = new RuleContainer<>();
        Rule<String, Integer> oldRule = new Rule<>() {
            @Override
            public Integer getResult(Integer result) {
                return 0;
            }

            @Override
            public boolean evaluate(String expression, Integer result) {
                return false;
            }
        };
        Rule<String, Integer> newRule = new Rule<>() {
            @Override
            public Integer getResult(Integer result) {
                return 1;
            }

            @Override
            public boolean evaluate(String expression, Integer result) {
                return true;
            }
        };
        container.appendRule(oldRule);
        container.replaceRule(newRule);
        assertTrue(container.getRules().contains(newRule));
        assertFalse(container.getRules().contains(oldRule));
    }

    @Test
    void givenNewRule_WhenReplaceRule_ThenNewRuleExists() {
        RuleContainer<String, Integer> container = new RuleContainer<>();
        Rule<String, Integer> newRule = new Rule<>() {
            @Override
            public Integer getResult(Integer result) {
                return 0;
            }

            @Override
            public boolean evaluate(String expression, Integer result) {
                return false;
            }
        };
        container.replaceRule(newRule);
        assertEquals(1, container.getRules().size());
        assertTrue(container.getRules().contains(newRule));
    }
}
