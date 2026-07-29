package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow event publishing concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowEventPublishing<I, O> {

    /**
     * Applies event publishing handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O publishEvent(I input);
}