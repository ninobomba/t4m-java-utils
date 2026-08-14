package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow observability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowObservability<I, O, E extends Throwable> {

    /**
     * Applies observability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O observe(I input) throws E;
}