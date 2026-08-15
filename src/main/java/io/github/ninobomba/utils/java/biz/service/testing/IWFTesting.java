package io.github.ninobomba.utils.java.biz.service.testing;

/**
 * Handles workflow testing concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFTesting<I, O, E extends Throwable> {

    /**
     * Applies testing handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O test(I input) throws E;
}