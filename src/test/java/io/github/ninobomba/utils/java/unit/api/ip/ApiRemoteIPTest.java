package io.github.ninobomba.utils.java.unit.api.ip;

import io.github.ninobomba.utils.java.api.ip.ApiRemoteIP;
import org.junit.jupiter.api.Test;

class ApiRemoteIPTest {
	
	@Test
	void getRemoteIpUsingApifyService ( ) {
		var ip = ApiRemoteIP.getRemoteIpUsingApifyService ();
		assert ip != null;
		System.out.println (  ip );
	}
	
	@Test
	void getRemoteIpUsingAwsService ( ) {
		var ip = ApiRemoteIP.getRemoteIpUsingAwsService ();
		assert ip != null;
		System.out.println (  ip );
	}
	
}
