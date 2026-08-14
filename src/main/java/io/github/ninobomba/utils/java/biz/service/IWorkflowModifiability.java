package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow modifiability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowModifiability<I, O, E extends Throwable> {

    /**
     * Applies modifiability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O modify(I input) throws E;
}