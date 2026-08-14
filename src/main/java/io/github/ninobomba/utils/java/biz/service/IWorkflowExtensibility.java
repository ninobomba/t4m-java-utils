package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow extensibility concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowExtensibility<I, O, E extends Throwable> {

    /**
     * Applies extensibility handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O extend(I input) throws E;
}