package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow availability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowAvailability<I, O, E extends Throwable> {

    /**
     * Applies availability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O ensureAvailability(I input) throws E;
}