package io.github.ninobomba.utils.java.biz.service.event;

/**
 * Handles workflow event persistence concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFEventPersistence<I, O, E extends Throwable> {

    /**
     * Applies event persistence handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O persistEvent(I input);
}