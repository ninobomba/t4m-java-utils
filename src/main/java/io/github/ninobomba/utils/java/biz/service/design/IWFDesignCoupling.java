package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow coupling concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignCoupling<I, O, E extends Throwable> {

    /**
     * Applies coupling handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O reduceCoupling(I input) throws E;
}