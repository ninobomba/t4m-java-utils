package io.github.ninobomba.utils.java.biz.service.processing;

/**
 * Handles workflow completion assertions.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFProcessingAssertCompletion<I, O, E extends Throwable> {

    /**
     * Asserts completion of the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O assertCompletion(I input) throws E;
}