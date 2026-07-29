package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow maintainability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowMaintainability<I, O> {

    /**
     * Applies maintainability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O maintain(I input);
}