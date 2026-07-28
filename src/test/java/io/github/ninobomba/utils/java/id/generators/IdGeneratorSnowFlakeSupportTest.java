package io.github.ninobomba.utils.java.id.generators;

import io.github.ninobomba.utils.java.id.generators.IdGeneratorSnowFlakeSupport;
import org.junit.jupiter.api.Test;

class IdGeneratorSnowFlakeSupportTest {

	@Test
	void getNextIdTest ( ) {
		var id = IdGeneratorSnowFlakeSupport.instance ( ).getNextId ( );
		System.out.println ( "IdGeneratorConcurrentLinkedQueueSupport: getNextId(): " + id );
		assert ( id > 0 );
	}


}