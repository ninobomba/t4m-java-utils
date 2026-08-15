package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow portability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignPortability<I, O, E extends Throwable> {

    /**
     * Applies portability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O port(I input) throws E;
}