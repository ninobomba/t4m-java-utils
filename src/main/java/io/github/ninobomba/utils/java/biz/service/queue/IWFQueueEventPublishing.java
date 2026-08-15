package io.github.ninobomba.utils.java.biz.service.queue;

/**
 * Handles workflow queue event publishing concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFQueueEventPublishing<I, O, E extends Throwable> {

    /**
     * Applies queue event publishing handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O publishQueueEvent(I input) throws E;
}