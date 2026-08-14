package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow configurability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowConfigurability<I, O, E extends Throwable> {

    /**
     * Applies configurability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O makeConfigurable(I input) throws E;
}