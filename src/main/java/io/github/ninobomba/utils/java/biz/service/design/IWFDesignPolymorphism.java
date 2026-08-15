package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow polymorphism concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignPolymorphism<I, O, E extends Throwable> {

    /**
     * Applies polymorphism handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O applyPolymorphism(I input) throws E;
}