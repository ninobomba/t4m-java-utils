package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow testing concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowTesting<I, O> {

    /**
     * Applies testing handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O test(I input);
}