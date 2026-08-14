package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow error handling concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowErrorHandling<I, O, E extends Throwable> {

    /**
     * Applies error handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O handleError(I input) throws E;
}