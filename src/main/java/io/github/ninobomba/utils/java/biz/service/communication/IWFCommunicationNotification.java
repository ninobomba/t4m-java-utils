package io.github.ninobomba.utils.java.biz.service.communication;

/**
 * Handles workflow notification concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFCommunicationNotification<I, O, E extends Throwable> {

    /**
     * Applies notification handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O notify(I input) throws E;
}