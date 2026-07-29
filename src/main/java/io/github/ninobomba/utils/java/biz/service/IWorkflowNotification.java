package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow notification concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowNotification<I, O> {

    /**
     * Applies notification handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O notifyWorkflow(I input);
}