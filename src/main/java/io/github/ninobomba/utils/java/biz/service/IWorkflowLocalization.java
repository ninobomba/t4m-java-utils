package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow localization concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowLocalization<I, O, E extends Throwable> {

    /**
     * Applies localization handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O localize(I input) throws E;
}