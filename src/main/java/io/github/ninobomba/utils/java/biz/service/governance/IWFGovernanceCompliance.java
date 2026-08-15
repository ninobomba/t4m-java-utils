package io.github.ninobomba.utils.java.biz.service.governance;

/**
 * Handles workflow compliance concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFGovernanceCompliance<I, O, E extends Throwable> {

    /**
     * Applies compliance handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O ensureCompliance(I input) throws E;
}