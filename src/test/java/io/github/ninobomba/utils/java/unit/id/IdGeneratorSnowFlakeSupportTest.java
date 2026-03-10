package io.github.ninobomba.utils.java.unit.id;

import io.github.ninobomba.utils.java.id.generators.IdGeneratorSnowFlakeSupport;

class IdGeneratorSnowFlakeSupportTest {

	//	@Test
	void getNextIdTest ( ) {
		var id = IdGeneratorSnowFlakeSupport.getINSTANCE ( ).getNextId ( );
		System.out.println ( "IdGeneratorConcurrentLinkedQueueSupport: getNextId(): " + id );
		assert ( id > 0 );
	}


}
