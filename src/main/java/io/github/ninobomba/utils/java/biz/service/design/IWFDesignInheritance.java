package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow inheritance concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignInheritance<I, O, E extends Throwable> {

    /**
     * Applies inheritance handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O inherit(I input) throws E;
}