package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow encapsulation concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowEncapsulation<I, O> {

    /**
     * Applies encapsulation handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O encapsulate(I input);
}