package io.github.ninobomba.utils.java.unit.time;

import io.github.ninobomba.utils.java.time.DateTimeUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeUtilsTest {

	@Test
	void getNameByActualTimestampTest ( ) {
		var name = DateTimeUtils.getNameByActualTimestamp ( );
		assertNotNull ( name );
		assertTrue ( name.matches ( "\\d{4}-\\d{2}-\\d{2}-\\d{2}-[AB]" ) );
	}

}
