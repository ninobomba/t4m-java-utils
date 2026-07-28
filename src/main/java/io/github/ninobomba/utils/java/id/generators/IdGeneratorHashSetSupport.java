package io.github.ninobomba.utils.java.id.generators;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGeneratorHashSetSupport provides unique ID generation with a lightweight in-memory buffer.
 * The class follows the singleton design pattern.
 */
public final class IdGeneratorHashSetSupport {

	// Replaced Set with a proper concurrent queue to safely consume elements (poll semantics)
	private static volatile Queue < Long > queue;

	// Properly published singleton
	private static volatile IdGeneratorHashSetSupport INSTANCE;

	private static final int MAX_QUEUE_SIZE = 10_000;
	private static final int MIN_QUEUE_SIZE_BEFORE_LOAD = 10;

	// Monotonic, process-unique counter, initialized near current time
	private static final AtomicLong COUNTER = new AtomicLong ( System.currentTimeMillis ( ) );

	private static final AtomicInteger queueSize = new AtomicInteger ( 0 );

	// Guard for load() to avoid concurrent refills
	private static final Object LOAD_LOCK = new Object ( );

	/**
	 * Private constructor initializes the buffer.
	 */
	private IdGeneratorHashSetSupport ( ) {
		load ( );
	}

	/**
	 * Returns the singleton instance of IdGeneratorHashSetSupport using double-checked locking.
	 */
	public static IdGeneratorHashSetSupport instance( ) {
		IdGeneratorHashSetSupport local = INSTANCE;
		if ( local == null ) {
			synchronized ( IdGeneratorHashSetSupport.class ) {
				local = INSTANCE;
				if ( local == null ) {
					local = new IdGeneratorHashSetSupport ( );
					INSTANCE = local;
				}
			}
		}
		return local;
	}

	/**
	 * Retrieves the next available unique ID from the queue.
	 * If the queue is low, it is topped up.
	 *
	 * @return The retrieved unique ID.
	 */
	public long getNextId ( ) {
		ensureQueueInitialized ( );
		if ( queueSize.get ( ) <= MIN_QUEUE_SIZE_BEFORE_LOAD ) {
			load ( );
		}
		Long id = queue.poll ( );
		if ( id != null ) {
			queueSize.decrementAndGet ( );
			return id;
		}
		return generateId ( );
	}

	/**
	 * Fills the buffer up to MAX_QUEUE_SIZE. Only one thread performs the refill at a time.
	 */
	private static void load ( ) {
		ensureQueueInitialized ( );
		if ( queueSize.get ( ) >= MAX_QUEUE_SIZE ) {
			return;
		}
		synchronized ( LOAD_LOCK ) {
			if ( queueSize.get ( ) >= MAX_QUEUE_SIZE ) {
				return;
			}
			int toGenerate = MAX_QUEUE_SIZE - queueSize.get ( );
			for ( int i = 0 ; i < toGenerate ; i++ ) {
				if ( queue.offer ( generateId ( ) ) ) {
					queueSize.incrementAndGet ( );
				}
			}
		}
	}

	private static void ensureQueueInitialized ( ) {
		if ( queue == null ) {
			synchronized ( IdGeneratorHashSetSupport.class ) {
				if ( queue == null ) {
					queue = new ConcurrentLinkedQueue <> ( );
				}
			}
		}
	}

	/**
	 * Generates a strictly monotonic, process-unique ID without sleeping.
	 * It returns max(prev + 1, currentTimeMillis) to ensure monotonicity across time regressions
	 * and high-throughput bursts within the same millisecond.
	 *
	 * @return The generated unique ID.
	 */
	private static long generateId ( ) {
		final long now = System.currentTimeMillis ( );
		return COUNTER.updateAndGet ( prev -> {
			long candidate = prev + 1;
			return Math.max ( candidate, now );
		} );
	}

}