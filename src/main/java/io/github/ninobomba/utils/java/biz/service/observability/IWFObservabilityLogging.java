package io.github.ninobomba.utils.java.biz.service.observability;

/**
 * Handles workflow logging concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFObservabilityLogging<I, O, E extends Throwable> {

    /**
     * Applies logging handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O logCollection(I input) throws E;
}