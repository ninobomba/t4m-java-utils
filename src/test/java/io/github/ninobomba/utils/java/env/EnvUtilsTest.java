package io.github.ninobomba.utils.java.env;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnvUtilsTest {

	private static final String TEST_PROPERTY = "env.utils.test.property";

	@AfterEach
	void tearDown ( ) {
		System.clearProperty ( TEST_PROPERTY );
	}

	@Test
	void getPropertyShouldReturnDefaultWhenMissing ( ) {
		var result = EnvUtils.getProperty ( TEST_PROPERTY, "default-value" );
		assertThat ( result ).isEqualTo ( "default-value" );
	}

	@Test
	void getPropertyShouldReturnSystemPropertyWhenPresent ( ) {
		System.setProperty ( TEST_PROPERTY, "configured-value" );

		var result = EnvUtils.getProperty ( TEST_PROPERTY, "default-value" );

		assertThat ( result ).isEqualTo ( "configured-value" );
	}

	@Test
	void getPropertyOrEnvVarShouldPreferSystemProperty ( ) {
		System.setProperty ( TEST_PROPERTY, "property-wins" );

		var result = EnvUtils.getPropertyOrEnvVar ( TEST_PROPERTY, "default-value" );

		assertThat ( result ).isEqualTo ( "property-wins" );
	}

	@Test
	void getEnvVarShouldReturnExistingEnvironmentValue ( ) {
		Map.Entry < String, String > sampleEnv = System.getenv ( ).entrySet ( ).stream ( )
				.filter ( e -> e.getValue ( ) != null && ! e.getValue ( ).isBlank ( ) )
				.findFirst ( )
				.orElseThrow ( );

		var result = EnvUtils.getEnvVar ( sampleEnv.getKey ( ), "default-value" );

		assertThat ( result ).isEqualTo ( sampleEnv.getValue ( ) );
	}

	@Test
	void methodsShouldRejectBlankVariableName ( ) {
		assertThrows ( IllegalArgumentException.class, ( ) -> EnvUtils.getProperty ( " ", "default" ) );
		assertThrows ( IllegalArgumentException.class, ( ) -> EnvUtils.getEnvVar ( " ", "default" ) );
		assertThrows ( IllegalArgumentException.class, ( ) -> EnvUtils.getPropertyOrEnvVar ( " ", "default" ) );
	}
}
