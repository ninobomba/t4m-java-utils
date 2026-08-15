package io.github.ninobomba.utils.java.biz.service.persistence;

/**
 * Asserts changes on a previously resolved entity and persists them.
 *
 * @param <I> input type
 * @param <O> output type
 * @param <E> checked or unchecked exception type
 */
@FunctionalInterface
public interface IWFPersistenceAssertChangesOnEntity<I, O, E extends Throwable> {
    O assertChangesOnEntity(I input) throws E;
}