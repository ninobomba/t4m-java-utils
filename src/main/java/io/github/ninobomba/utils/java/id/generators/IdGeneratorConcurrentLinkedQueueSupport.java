package io.github.ninobomba.utils.java.id.generators;

import lombok.SneakyThrows;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The IdGeneratorConcurrentLinkedQueueSupport class is responsible for generating unique IDs.
 * It uses a queue to store generated IDs and reloads the queue when it becomes empty or below a certain threshold.
 * The class follows the singleton pattern, meaning there can only be one instance of IdGeneratorConcurrentLinkedQueueSupport.
 */
public final class IdGeneratorConcurrentLinkedQueueSupport {

	private static final ConcurrentLinkedQueue < Long > queue = new ConcurrentLinkedQueue <> ( );
	private static final AtomicInteger queueSize = new AtomicInteger ( 0 );
	private static final AtomicLong COUNTER = new AtomicLong ( System.currentTimeMillis ( ) );
	private static final Object LOAD_LOCK = new Object ( );

	private static volatile IdGeneratorConcurrentLinkedQueueSupport INSTANCE;

	private static final int MAX_QUEUE_SIZE = 10_000;
	private static final int MIN_QUEUE_SIZE_BEFORE_LOAD = 10;
	private static final long WAIT_TIME = 1L;

	/**
	 * The IdGeneratorConcurrentLinkedQueueSupport class is responsible for generating unique IDs.
	 * It uses a queue to store generated IDs and reloads the queue when it becomes empty or below a certain threshold.
	 * The class follows the singleton pattern, meaning there can only be one instance of IdGeneratorConcurrentLinkedQueueSupport.
	 */
	private IdGeneratorConcurrentLinkedQueueSupport ( ) {
		load ( );
	}

	/**
	 * Returns an instance of IdGeneratorConcurrentLinkedQueueSupport.
	 *
	 * @return the instance of IdGeneratorConcurrentLinkedQueueSupport
	 */
	public static IdGeneratorConcurrentLinkedQueueSupport instance( ) {
		if ( Objects.isNull ( INSTANCE ) ) {
			synchronized ( IdGeneratorConcurrentLinkedQueueSupport.class ) {
				if ( Objects.isNull ( INSTANCE ) ) {
					INSTANCE = new IdGeneratorConcurrentLinkedQueueSupport ( );
				}
			}
		}
		return INSTANCE;
	}

	/**
	 * Returns the next ID from the IdGeneratorConcurrentLinkedQueueSupport queue.
	 * <p>
	 * If the queue is empty or its size is below a certain threshold, the method calls the load() method to reload the queue.
	 * It then retrieves the next ID from the queue using the poll() method.
	 * If the retrieved ID is null, the method calls the generateId() method to generate a new ID.
	 *
	 * @return The next ID from the IdGeneratorConcurrentLinkedQueueSupport queue.
	 */
	public long getNextId ( ) {
		if ( queueSize.get ( ) <= MIN_QUEUE_SIZE_BEFORE_LOAD )
			load ( );
		Long id = queue.poll ( );
		if ( Objects.nonNull ( id ) ) {
			queueSize.decrementAndGet ( );
			return id;
		}
		return generateId ( );
	}

	/**
	 * Loads the IdGeneratorConcurrentLinkedQueueSupport with unique generated IDs.
	 * It generates IDs using the generateId method of IdGeneratorConcurrentLinkedQueueSupport class,
	 * limits the stream to half of the MAX_QUEUE_SIZE,
	 * and puts the unique IDs into the queue using the offer method safely.
	 * Note: This method does not return any value.
	 */
	private static void load ( ) {
		if ( queueSize.get ( ) >= MAX_QUEUE_SIZE / 2 ) {
			return;
		}
		synchronized ( LOAD_LOCK ) {
			if ( queueSize.get ( ) >= MAX_QUEUE_SIZE / 2 ) {
				return;
			}
			int toGenerate = ( MAX_QUEUE_SIZE / 2 ) - queueSize.get ( );
			for ( int i = 0 ; i < toGenerate ; i++ ) {
				queue.offer ( generateId ( ) );
				queueSize.incrementAndGet ( );
			}
		}
	}

	/**
	 * Generates a unique ID.
	 * The method sleeps for WAIT_TIME milliseconds,
	 * then generates a unique ID using a monotonic counter.
	 *
	 * @return The generated unique ID.
	 */
	@SneakyThrows
	private static long generateId ( ) {
		TimeUnit.MILLISECONDS.sleep ( WAIT_TIME );
		long now = System.currentTimeMillis ( );
		return COUNTER.updateAndGet ( prev -> Math.max ( prev + 1, now ) );
	}

}