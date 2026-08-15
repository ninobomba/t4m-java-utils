package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow composability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignComposability<I, O, E extends Throwable> {

    /**
     * Applies composability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O compose(I input) throws E;
}