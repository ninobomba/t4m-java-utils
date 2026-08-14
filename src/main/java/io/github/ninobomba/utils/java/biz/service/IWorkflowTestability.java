package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow testability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowTestability<I, O, E extends Throwable> {

    /**
     * Applies testability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O improveTestability(I input) throws E;
}