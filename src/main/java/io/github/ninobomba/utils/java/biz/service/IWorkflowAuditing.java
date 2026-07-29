package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow auditing concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowAuditing<I, O> {

    /**
     * Applies auditing handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O audit(I input);
}