package io.github.ninobomba.utils.java.events.stack;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventFactoryTest {

	private final ObjectMapper objectMapper = new ObjectMapper ( );

	@Test
	void fromJsonAsMapShouldReturnMap ( ) {
		var json = "{\"entityName\":\"order\",\"eventType\":\"A\",\"entityId\":\"1\"}";

		var map = EventFactory.fromJsonAsMap ( objectMapper, json );

		assertThat ( map ).containsEntry ( "entityName", "order" );
	}

	@Test
	void fromJsonToEventShouldParseValidPayload ( ) {
		var json = "{" +
				"\"id\":\"event-1\"," +
				"\"name\":\"created\"," +
				"\"type\":\"A\"," +
				"\"entityIdentifier\":\"order-1\"," +
				"\"entityName\":\"order\"," +
				"\"entityId\":\"order-1\"," +
				"\"eventType\":\"A\"," +
				"\"timestamp\":\"2026-07-10T10:15:30\"" +
				"}";

		var event = EventFactory.fromJsonToEvent ( objectMapper, json );

		assertThat ( event.id ( ) ).isEqualTo ( "event-1" );
		assertThat ( event.entityIdentifier ( ) ).isEqualTo ( "order-1" );
		assertThat ( event.timestamp ( ) ).isEqualTo ( LocalDateTime.parse ( "2026-07-10T10:15:30" ) );
	}

	@Test
	void fromJsonToEventShouldThrowWhenRequiredAttributesAreMissing ( ) {
		var json = "{\"eventType\":\"A\"}";

		var exception = assertThrows ( IllegalArgumentException.class,
				( ) -> EventFactory.fromJsonToEvent ( objectMapper, json ) );

		assertThat ( exception.getMessage ( ) ).contains ( "missing required event attributes" );
	}

	@Test
	void fromJsonToEventShouldThrowWhenEventTypeIsUnsupported ( ) {
		var json = "{" +
				"\"entityName\":\"order\"," +
				"\"entityId\":\"order-1\"," +
				"\"eventType\":\"X\"" +
				"}";

		var exception = assertThrows ( UnsupportedOperationException.class,
				( ) -> EventFactory.fromJsonToEvent ( objectMapper, json ) );

		assertThat ( exception.getMessage ( ) ).contains ( "Unsupported event type" );
	}
}
