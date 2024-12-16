import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Properties;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
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
    }

    @ParameterizedTest
    @MethodSource("values")
    void getProperty(String key, String value) {
        assertNull(System.getProperty(key));
        System.setProperty(key, value);
        assertEquals(value, System.getProperty(key));
    }

    @ParameterizedTest
    @MethodSource("values")
    void setProperty(String key, String value) {
        System.setProperty(key, value);
        assertEquals(value, System.getProperty(key));
        System.clearProperty(key);
    }

    @Test
    void getProperties() {
        assertNotNull(System.getProperties());
        assertTrue(System.getProperties().size() > 0);
    }

    @Test
    void setProperties() {
        Properties newProperties = new Properties();
        newProperties.setProperty("user.pokemon", "pikachu");
        System.setProperties(newProperties);
        assertEquals(newProperties, System.getProperties());
    }
}
