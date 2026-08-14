package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow normalizing concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowNormalize<I, O, E extends Throwable> {

    /**
     * Applies normalizing handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O normalize(I input) throws E;
}