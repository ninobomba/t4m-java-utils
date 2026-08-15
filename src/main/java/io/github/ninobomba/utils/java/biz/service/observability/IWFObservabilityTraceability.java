package io.github.ninobomba.utils.java.biz.service.observability;

/**
 * Handles workflow traceability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFObservabilityTraceability<I, O, E extends Throwable> {

    /**
     * Applies traceability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O trace(I input) throws E;
}