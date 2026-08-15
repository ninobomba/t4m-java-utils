package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow abstraction concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignAbstraction<I, O, E extends Throwable> {

    /**
     * Applies abstraction handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O abstractWorkflow(I input) throws E;
}