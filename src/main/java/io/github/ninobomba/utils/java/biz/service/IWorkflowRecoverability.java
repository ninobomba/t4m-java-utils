package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow recoverability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowRecoverability<I, O> {

    /**
     * Applies recoverability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O recover(I input);
}