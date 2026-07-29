package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow authorization concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowAuthorization<I, O> {

    /**
     * Applies authorization handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O authorize(I input);
}