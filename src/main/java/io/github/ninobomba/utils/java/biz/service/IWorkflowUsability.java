package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow usability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowUsability<I, O> {

    /**
     * Applies usability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O improveUsability(I input);
}