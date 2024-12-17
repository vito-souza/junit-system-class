package old;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SystemTest {

	@ParameterizedTest
	@ValueSource(strings = { "PATH", "foo" })
	void getenv(String env) {
		if ("foo".equals(env)) {
			assertNull(System.getenv(env));
		} else {
			assertNotNull(System.getenv(env));
		}
	}

	@Test
	void currentTimeMillis() throws InterruptedException {
		long time = System.currentTimeMillis();
		Thread.sleep(50);

		assertTrue(System.currentTimeMillis() > time);
	}

	@Test
	void identifyHashCode() {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = obj1;

		assertEquals(System.identityHashCode(obj1), System.identityHashCode(obj3));
		assertNotEquals(System.identityHashCode(obj1), System.identityHashCode(obj2));
	}
}
