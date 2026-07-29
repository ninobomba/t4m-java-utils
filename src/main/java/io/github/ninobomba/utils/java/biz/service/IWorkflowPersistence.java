package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow persistence concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowPersistence<I, O> {

    /**
     * Applies persistence handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O persist(I input);
}