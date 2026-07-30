package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow logging concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowLogging<I, O> {

    /**
     * Applies logging handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O logExecution(I input);
}