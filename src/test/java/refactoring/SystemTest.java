package refactoring;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintStream;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SystemTest {

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(SystemData.NEW_OUT));
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
	void shouldThrowArrayStoreExceptionWhenTryingToCopyArrayToDifferentTypeDestiny() {
		assertThrows(ArrayStoreException.class, () -> {
			System.arraycopy(SystemData.INT_ARRAY, 0, SystemData.DOUBLE_DESTINATION_ARRAY, 0,
					SystemData.INT_ARRAY.length);
		});
	}

	@Test
	void shouldClearPropertyWhenPropertyIsSet() {
		System.setProperty(SystemData.PROPERTY_KEY.getValue(), SystemData.PROPERTY_VALUE.getValue());
		System.clearProperty(SystemData.PROPERTY_KEY.getValue());

		assertNull(System.getProperty(SystemData.PROPERTY_KEY.getValue()));
	}

	@Test
	void shouldThrowIllegalArgumentException() {
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
	void shouldIdentifyObjectHashcode() {
		assertEquals(System.identityHashCode(SystemData.OBJ_ONE), System.identityHashCode(SystemData.OBJ_THREE));
		assertNotEquals(System.identityHashCode(SystemData.OBJ_ONE), System.identityHashCode(SystemData.OBJ_TWO));
	}

	@Test
	void shouldReturnZeroWhenIdentifyingHashCodeOfNullReference() {
		assertEquals(0, System.identityHashCode(SystemData.NULL_OBJECT));
	}

	@Test
	void shouldThrowNullPointerExceptionWhenAttemptingToQueryPresenceOfNullKey() {
		Map<String, String> env = System.getenv();

		assertThrows(NullPointerException.class, () -> {
			env.get(null);
		});
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void shouldThrowClassCastExceptionWhenAttemptingToQueryValueThatIsNotString() {
		Map<String, String> env = System.getenv();

		assertThrows(ClassCastException.class, () -> {
			env.get(10);
		});
	}
}
