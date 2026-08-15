package io.github.ninobomba.utils.java.biz.service.processing;

/**
 * Sanitizes workflow input before it is processed.
 *
 * @param <I> input type
 * @param <O> sanitized output type
 */
@FunctionalInterface
public interface IWFProcessingInputSanitizer<I, O, E extends Throwable> {

    /**
     * Sanitizes the given input.
     *
     * @param input input to sanitize; null-handling is implementation-specific
     * @return sanitized output
     */
    O sanitize(I input) throws E;
}