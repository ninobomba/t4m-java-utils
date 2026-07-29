package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow security concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowSecurity<I, O> {

    /**
     * Applies security handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O secure(I input);
}