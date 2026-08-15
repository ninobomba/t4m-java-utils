package io.github.ninobomba.utils.java.biz.service.quality;

/**
 * Handles workflow usability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFQualityUsability<I, O, E extends Throwable> {

    /**
     * Applies usability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O improveUsability(I input) throws E;
}