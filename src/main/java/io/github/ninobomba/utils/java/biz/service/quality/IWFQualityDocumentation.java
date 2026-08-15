package io.github.ninobomba.utils.java.biz.service.quality;

/**
 * Handles workflow documentation concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFQualityDocumentation<I, O, E extends Throwable> {

    /**
     * Applies documentation handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O document(I input) throws E;
}