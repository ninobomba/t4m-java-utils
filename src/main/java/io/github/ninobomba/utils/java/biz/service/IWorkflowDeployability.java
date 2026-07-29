package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow deployability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowDeployability<I, O> {

    /**
     * Applies deployability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O deploy(I input);
}