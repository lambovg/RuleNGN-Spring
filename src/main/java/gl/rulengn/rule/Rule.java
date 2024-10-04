package gl.rulengn.rule;

/**
 * Rule interface that is implemented for each family of rules.
 *
 * @param <E> Expression
 * @param <R> Result
 */
public interface Rule<E, R> {
    /**
     * Returns set result in given
     *
     * @return Result
     */
    default R getResult(R result) {
        return result;
    }

    /**
     * Evaluates given rule provided expression T. If result is evaluated R is populated.
     *
     * @param expression Expression to evaluate
     * @param result     Result to populate
     * @return true | false for given expression
     */
    boolean evaluate(E expression, R result);
}
