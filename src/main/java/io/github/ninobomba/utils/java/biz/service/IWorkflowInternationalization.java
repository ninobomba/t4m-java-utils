package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow internationalization concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowInternationalization<I, O, E extends Throwable> {

    /**
     * Applies internationalization handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O internationalize(I input) throws E;
}