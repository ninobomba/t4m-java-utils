package io.github.ninobomba.utils.java.biz.service.observability;

/**
 * Handles workflow monitoring concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFObservabilityMonitoring<I, O, E extends Throwable> {

    /**
     * Applies monitoring handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O monitor(I input) throws E;
}