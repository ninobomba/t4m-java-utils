package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow configuration concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowConfiguration<I, O, E extends Throwable> {

    /**
     * Applies configuration handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O configure(I input) throws E;
}