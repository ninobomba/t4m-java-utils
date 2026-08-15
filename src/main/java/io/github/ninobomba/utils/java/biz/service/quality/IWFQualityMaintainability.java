package io.github.ninobomba.utils.java.biz.service.quality;

/**
 * Handles workflow maintainability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFQualityMaintainability<I, O, E extends Throwable> {

    /**
     * Applies maintainability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O maintain(I input) throws E;
}