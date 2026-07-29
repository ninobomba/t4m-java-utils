package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow abstraction concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowAbstraction<I, O> {

    /**
     * Applies abstraction handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O abstractWorkflow(I input);
}