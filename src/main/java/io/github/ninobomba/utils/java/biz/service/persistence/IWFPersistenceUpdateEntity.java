package io.github.ninobomba.utils.java.biz.service.persistence;

/**
 * Updates and persists a previously resolved entity.
 *
 * @param <I> input type
 * @param <O> output type
 * @param <E> checked or unchecked exception type
 */
@FunctionalInterface
public interface IWFPersistenceUpdateEntity<I, O, E extends Throwable> {
    O updateEntity(I input) throws E;
}