package io.github.ninobomba.utils.java.id;

import io.github.ninobomba.utils.java.id.generators.IdGeneratorConcurrentLinkedQueueSupport;
import io.github.ninobomba.utils.java.id.generators.IdGeneratorHashSetSupport;
import io.github.ninobomba.utils.java.id.generators.IdGeneratorLUUIDSupport;
import io.github.ninobomba.utils.java.id.generators.IdGeneratorSnowFlakeSupport;

public enum IdGenerator {

	INSTANCE;

	private final IdGeneratorConcurrentLinkedQueueSupport idGeneratorConcurrentLinkedQueueSupport = IdGeneratorConcurrentLinkedQueueSupport.getINSTANCE ( );
	private final IdGeneratorHashSetSupport idGeneratorHashSetSupport = IdGeneratorHashSetSupport.getINSTANCE ( );
	private final IdGeneratorSnowFlakeSupport idGeneratorSnowFlakeSupport = IdGeneratorSnowFlakeSupport.getINSTANCE ( );
	private final IdGeneratorLUUIDSupport idGeneratorLUUIDSupport = IdGeneratorLUUIDSupport.getInstance ( );

	private long getNextIdConcurrentLinkedQueueSupport ( ) {
		return idGeneratorConcurrentLinkedQueueSupport.getNextId ( );
	}

	private long getNextIdHashSetSupport ( ) {
		return idGeneratorHashSetSupport.getNextId ( );
	}

	private long getNextIdSnowFlakeSupport ( ) {
		return idGeneratorSnowFlakeSupport.getNextId ( );
	}

	public String getNextIdLUUID ( ) {
		return idGeneratorLUUIDSupport.getNextId ( );
	}

	public String getNextIdAsUUID ( IdGeneratorUUIDSupport.TYPE uuidType ) {
		return IdGeneratorUUIDSupport.INSTANCE.getNextId ( uuidType );
	}

	public long getNextId ( TYPE type ) {
		return switch ( type ) {
			case CONCURRENT_LINKED_QUEUE -> getNextIdConcurrentLinkedQueueSupport ( );
			case HASH_SET -> getNextIdHashSetSupport ( );
			case SNOW_FLAKE -> getNextIdSnowFlakeSupport ( );
			default -> throw new UnsupportedOperationException ( "Unsupported id type: " + type );
		};
	}

	public enum TYPE {
		CONCURRENT_LINKED_QUEUE, HASH_SET, SNOW_FLAKE, LUUID, UUID;
	}

}
