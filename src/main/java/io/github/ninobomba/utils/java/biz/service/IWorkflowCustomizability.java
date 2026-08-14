package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow customizability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowCustomizability<I, O, E extends Throwable> {

    /**
     * Applies customizability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O customize(I input) throws E;
}