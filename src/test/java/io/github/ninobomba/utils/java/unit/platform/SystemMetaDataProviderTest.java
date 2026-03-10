package io.github.ninobomba.utils.java.unit.platform;


import io.github.ninobomba.utils.java.platform.SystemMetaDataProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SystemMetaDataProviderTest {
	
	@Test
	void getPropertiesTest ( ) {
		var properties = SystemMetaDataProvider.getProperties ( );
		assertThat ( properties ).isNotNull ( );
		properties.forEach ( ( k, v ) -> System.out.println ( k + ":" + v ) );
	}
	
}
