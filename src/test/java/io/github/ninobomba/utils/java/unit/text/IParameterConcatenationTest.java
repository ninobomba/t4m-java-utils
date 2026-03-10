package io.github.ninobomba.utils.java.unit.text;

import io.github.ninobomba.utils.java.text.IParameterConcatenation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IParameterConcatenationTest {

	@Test
	void formatTest ( ) {
		var result = IParameterConcatenation.format ( "Hello {}, welcome to {}!", "John", "T4M" );
		assertEquals ( "Hello John, welcome to T4M!", result );
	}

	@Test
	void formatWithSpecialCharactersTest ( ) {
		var result = IParameterConcatenation.format ( "Value: {}", "a.b$c" );
		assertEquals ( "Value: a.b$c", result );
	}

	@Test
	void formatWithNullParametersTest ( ) {
		var result = IParameterConcatenation.format ( "Value: {}", ( Object[] ) null );
		assertEquals ( "Value: {}", result );
	}

	@Test
	void formatWithEmptyParametersTest ( ) {
		var result = IParameterConcatenation.format ( "Value: {}" );
		assertEquals ( "Value: {}", result );
	}

}
