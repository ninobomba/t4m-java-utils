package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow accessibility concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowAccessibility<I, O> {

    /**
     * Applies accessibility handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O improveAccessibility(I input);
}