package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow interoperability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignInteroperability<I, O, E extends Throwable> {

    /**
     * Applies interoperability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O interoperate(I input) throws E;
}