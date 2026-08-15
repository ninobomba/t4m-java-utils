package io.github.ninobomba.utils.java.biz.service.processing;

/**
 * Handles workflow validation concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFProcessingValidation<I, O, E extends Throwable> {

    /**
     * Applies validation handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O validate(I input) throws E;
}