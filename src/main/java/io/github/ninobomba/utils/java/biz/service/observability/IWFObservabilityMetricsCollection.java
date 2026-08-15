package io.github.ninobomba.utils.java.biz.service.observability;

/**
 * Handles workflow metrics concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFObservabilityMetricsCollection<I, O, E extends Throwable> {

    /**
     * Applies metrics handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O collectMetrics(I input) throws E;
}