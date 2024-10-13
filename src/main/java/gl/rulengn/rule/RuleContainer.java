package gl.rulengn.rule;

import lombok.Data;
import lombok.NonNull;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The RuleContainer class manages a collection of Rule objects.
 * It provides methods to add rules either by appending them to the existing set
 * or by clearing the current set and adding a new rule.
 */
@Data
public class RuleContainer<E, R> {
    private final Set<Rule<E, R>> rules = new LinkedHashSet<>();


    public RuleContainer<E, R> appendRule(@NonNull Rule<E, R> rule) {
        rules.add(rule);
        return this;
    }

    public RuleContainer<E, R> replaceRule(@NonNull Rule<E, R> rule) {
        rules.clear();
        rules.add(rule);
        return this;
    }
}
