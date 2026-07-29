package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow versioning concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowVersioning<I, O> {

    /**
     * Applies versioning handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O version(I input);
}