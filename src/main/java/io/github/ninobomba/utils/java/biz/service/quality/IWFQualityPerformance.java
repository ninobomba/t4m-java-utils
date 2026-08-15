package io.github.ninobomba.utils.java.biz.service.quality;

/**
 * Handles workflow performance concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFQualityPerformance<I, O, E extends Throwable> {

    /**
     * Applies performance handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O optimizePerformance(I input) throws E;
}