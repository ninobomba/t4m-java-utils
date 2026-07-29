package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow reliability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowReliability<I, O> {

    /**
     * Applies reliability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O ensureReliability(I input);
}