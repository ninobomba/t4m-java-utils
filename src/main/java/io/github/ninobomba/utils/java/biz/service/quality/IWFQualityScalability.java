package io.github.ninobomba.utils.java.biz.service.quality;

/**
 * Handles workflow scalability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFQualityScalability<I, O, E extends Throwable> {

    /**
     * Applies scalability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O scale(I input) throws E;
}