package io.github.ninobomba.utils.java.biz.service.processing;

/**
 * Handles workflow execution concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFProcessingExecution<I, O, E extends Throwable> {

    /**
     * Applies execution handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O execute(I input) throws E;
}