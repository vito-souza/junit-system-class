import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PropertyTest {

    private static Stream<String[]> values() {
        return Stream.of(
                new String[] { "blah", "blech" },
                new String[] { "foo", "bar" },
                new String[] { "key", "value" });
    }

    @ParameterizedTest
    @MethodSource("values")
    void clearProperty(String key, String value) {
        System.setProperty(key, value);
        assertEquals(value, System.getProperty(key));

        System.clearProperty(key);

        assertNull(System.getProperty(key));
        assertThrows(NullPointerException.class, () -> System.clearProperty(null));
        assertThrows(IllegalArgumentException.class, () -> System.clearProperty(""));
    }

    @ParameterizedTest
    @MethodSource("values")
    void getProperty(String key, String value) {
        assertEquals(value, System.getProperty(key));
        assertThrows(NullPointerException.class, () -> System.getProperty(null));
        assertThrows(IllegalArgumentException.class, () -> System.getProperty(""));
    }

    @ParameterizedTest
    @MethodSource("values")
    void setProperty(String key, String value) {
        System.setProperty(key, value);

        assertEquals(value, System.getProperty(key));
        assertThrows(NullPointerException.class, () -> System.setProperty(null, null));
        assertThrows(IllegalArgumentException.class, () -> System.setProperty("", ""));
    }
}
