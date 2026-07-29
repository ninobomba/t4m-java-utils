package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow orchestration concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowOrchestration<I, O> {

    /**
     * Applies orchestration handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O orchestrate(I input);
}