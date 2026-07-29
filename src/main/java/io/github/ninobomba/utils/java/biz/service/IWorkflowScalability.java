package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow scalability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowScalability<I, O> {

    /**
     * Applies scalability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O scale(I input);
}