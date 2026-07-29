package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow traceability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowTraceability<I, O> {

    /**
     * Applies traceability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O trace(I input);
}