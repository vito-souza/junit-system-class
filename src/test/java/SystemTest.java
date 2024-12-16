import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SystemTest {

	@Test
	void arraycopy() {
		int[] source = { 0, 1, 2 };
		int[] destination = new int[3];

		System.arraycopy(source, 0, destination, 0, source.length);

		assertArrayEquals(new int[] { 0, 1, 2 }, destination);
	}

	@ParameterizedTest
	@ValueSource(strings = { "PATH", "foo" })
	void getenv(String env) {
		if ("foo".equals(env)) {
			assertNull(System.getenv(env));
		} else {
			assertNotNull(System.getenv(env));
		}
		assertNotNull(System.getenv());
	}

	@Test
	void currentTimeMillis() throws InterruptedException {
		long time = System.currentTimeMillis();
		Thread.sleep(50);
		assertTrue(System.currentTimeMillis() > time);
	}
}
