package io.github.ninobomba.utils.java.biz.service;

import io.github.ninobomba.utils.java.patterns.process.OperationResult;

/**
 * Sanitizes workflow input before it is processed.
 *
 * @param <I> input type
 * @param <O> sanitized output type
 */
@FunctionalInterface
public interface IWorkflowInputSanitizer<I, O> {

    /**
     * Sanitizes the given input.
     *
     * @param input input to sanitize; null-handling is implementation-specific
     * @return sanitized output
     */
    O sanitize(I input);
}