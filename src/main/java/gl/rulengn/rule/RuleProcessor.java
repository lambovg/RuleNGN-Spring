package gl.rulengn.rule;

public interface RuleProcessor<R> {

    /**
     * Process family rules.
     */
    R process();

    /**
     * @return true if the processing was successful.
     */
    boolean isSuccess();
}
