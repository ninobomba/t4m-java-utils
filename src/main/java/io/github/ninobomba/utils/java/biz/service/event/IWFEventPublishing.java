package io.github.ninobomba.utils.java.biz.service.event;

/**
 * Handles workflow event publishing concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFEventPublishing<I, O, E extends Throwable> {

    /**
     * Applies event publishing handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O publishEvent(I input) throws E;
}