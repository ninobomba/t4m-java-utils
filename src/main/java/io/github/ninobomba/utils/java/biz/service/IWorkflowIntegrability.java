package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow integrability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowIntegrability<I, O> {

    /**
     * Applies integrability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O integrate(I input);
}