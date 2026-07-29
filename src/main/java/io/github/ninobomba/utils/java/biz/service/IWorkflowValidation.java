package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow validation concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowValidation<I, O> {

    /**
     * Applies validation handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O validate(I input);
}