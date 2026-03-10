package io.github.ninobomba.utils.java.unit.api.ip;

import io.github.ninobomba.utils.java.api.ip.ApiRemoteIP;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ApiRemoteIpTest {
	@Test
	void getRemoteIpByAwsTest ( ) {
		var ip = ApiRemoteIP.getRemoteIpUsingAwsService ( );
		assertThat ( ip ).isNotBlank ( );
	}
}
