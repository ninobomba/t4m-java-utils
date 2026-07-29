package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow inheritance concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowInheritance<I, O> {

    /**
     * Applies inheritance handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O inherit(I input);
}