
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SystemTest {

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(SystemData.NEW_OUT));
		SystemData.NEW_OUT.reset();
	}

	@AfterEach
	public void tearDown() {
		System.setOut(SystemData.ORIGINAL_OUT);
	}

	@Test
	void shouldCopyArrayWhenTypesAreEqual() {
		System.arraycopy(SystemData.INT_ARRAY, 0, SystemData.INT_DESTINATION_ARRAY, 0, SystemData.INT_ARRAY.length);
		assertArrayEquals(SystemData.INT_ARRAY, SystemData.INT_DESTINATION_ARRAY);
	}

	@Test
	void shouldThrowArrayStoreExceptionWhenCopyingToDifferentType() {
		assertThrows(ArrayStoreException.class, () -> {
			System.arraycopy(SystemData.INT_ARRAY, 0, SystemData.DOUBLE_DESTINATION_ARRAY, 0,
					SystemData.INT_ARRAY.length);
		});
	}

	@Test
	void shouldClearPropertyWhenSet() {
		System.setProperty(SystemData.PROPERTY_KEY.getValue(), SystemData.PROPERTY_VALUE.getValue());
		System.clearProperty(SystemData.PROPERTY_KEY.getValue());

		assertNull(System.getProperty(SystemData.PROPERTY_KEY.getValue()));
	}

	@Test
	void shouldThrowIllegalArgumentExceptionWhenClearingEmptyKey() {
		assertThrows(IllegalArgumentException.class, () -> {
			System.clearProperty("");
		});
	}

	@Test
	void shouldInitializeSystemPropertiesWhenNoneExist() {
		System.setProperties(null);

		assertNotNull(System.getProperties());
		assertTrue(System.getProperties().containsKey("user.name"));
	}

	@Test
	void shouldInitializeSystemPropertiesWithOSInfo() {
		System.setProperties(null);

		assertNotNull(System.getProperties());
		assertNotNull(System.getProperty("os.name"));
	}

	@Test
	void shouldIdentifyObjectHashcode() {
		assertEquals(System.identityHashCode(SystemData.OBJ_ONE), System.identityHashCode(SystemData.OBJ_THREE));
		assertNotEquals(System.identityHashCode(SystemData.OBJ_ONE), System.identityHashCode(SystemData.OBJ_TWO));
	}

	@Test
	void shouldReturnZeroHashCodeForNullReference() {
		assertEquals(0, System.identityHashCode(SystemData.NULL_OBJECT));
	}

	@Test
	void shouldThrowNullPointerExceptionForNullKeyInEnv() {
		Map<String, String> env = System.getenv();
		assertThrows(NullPointerException.class, () -> env.get(null));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void shouldThrowClassCastExceptionForInvalidEnvKeyType() {
		Map<String, String> env = System.getenv();
		assertThrows(ClassCastException.class, () -> env.get(10));
	}

	@Test
	void shouldReturnSystemDependentLineSeparator() {
		System.setProperty("line.separator", SystemData.WIN_LINE_SEPARATOR.getValue());
		assertEquals(System.lineSeparator(), SystemData.UNIX_LINE_SEPARATOR.getValue());
	}

	@Test
	void shouldContainSpecificSystemEnvVariable() {
		assertTrue(System.getenv().containsKey("GMAIL_USERNAME"));
	}

	@Test
	void shouldReturnDefaultValueForNonExistingKey() {
		assertEquals("default", System.getProperty("nonExistentKey", "default"));
	}

	@Test
	void shouldCaptureTimeProgressionWithNanoTime() {
		long firstCall = System.nanoTime();
		long secondCall = System.nanoTime();
		assertTrue(secondCall > firstCall);
	}

	@Test
	void shouldReturnSameValueForConsecutiveCallsWithinGranularity() {
		long firstCall = System.currentTimeMillis();
		long secondCall = System.currentTimeMillis();
		assertEquals(secondCall, firstCall);
	}

	@Test
	void shouldContainSystemDependentPathSeparator() {
		String path;

		if (SystemData.OS_NAME.contains("win")) {
			path = SystemData.PATH_ENV.replaceAll(":", "");
			assertTrue(path.contains(SystemData.WIN_PATH_SEPARATOR.getValue()));
		} else {
			path = SystemData.PATH_ENV;
			assertTrue(path.contains(SystemData.UNIX_PATH_SEPARATOR.getValue()));
		}
	}

	@Test
	void shouldReturnCorrectFileSeparatorInAbsolutePath() throws IOException {
		File file = File.createTempFile("temp", ".tmp");

		if (SystemData.OS_NAME.contains("win")) {
			assertTrue(file.getAbsolutePath().contains(SystemData.WIN_FILE_SEPARATOR.getValue()));
		} else {
			assertTrue(file.getAbsolutePath().contains(SystemData.UNIX_FILE_SEPARATOR.getValue()));
		}

		file.delete();
	}

	@Test
	void shouldPrintNullWhenObjectIsNull() {
		System.out.print(SystemData.NULL_OBJECT);
		String output = SystemData.NEW_OUT.toString();

		assertEquals("null", output);
	}

	@Test
	void shouldPrintHashCodeWhenObjectDoesNotOverrideToString() {
		System.out.print(SystemData.OBJ_ONE);
		String output = SystemData.NEW_OUT.toString();

		assertTrue(output.matches(".*@([0-9a-fA-F]+)"));
	}
}
