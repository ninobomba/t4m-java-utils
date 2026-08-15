package io.github.ninobomba.utils.java.biz.service.persistence;

/**
 * Resolves or loads a persistence entity from a given input.
 *
 * @param <I> input type
 * @param <O> output type
 * @param <E> checked or unchecked exception type
 */
@FunctionalInterface
public interface IWFPersistenceResolveEntity<I, O, E extends Throwable> {
    O resolveEntity(I input) throws E;
}