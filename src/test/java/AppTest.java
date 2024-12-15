import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AppTest {

    @Test
    void arraycopy() {
        int[] source = { 0, 1, 2 };
        int[] destination = new int[3];

        System.arraycopy(source, 0, destination, 0, source.length);

        assertArrayEquals(new int[] { 0, 1, 2 }, destination);
    }

    @Test
    void clearProperty() {
        System.setProperty("blah", "blech");
        Assertions.assertEquals("blech", System.getProperty("blah"));

        System.clearProperty("blah");
        assertNull(System.getProperty("blah"));

        assertThrows(NullPointerException.class, () -> System.clearProperty(null));
        assertThrows(IllegalArgumentException.class, () -> System.clearProperty(""));
    }

    @ParameterizedTest
    @ValueSource(strings = { "PATH", "foo" })
    void getenv(String env) {
        if ("foo".equals(env))
            assertNull(System.getenv(env));
        else
            assertNotNull(System.getenv(env));
    }

    @Test
    void setProperty() {
        System.setProperty("blah", "blech");

        assertEquals("blech", System.getProperty("blah"));
        assertThrows(NullPointerException.class, () -> System.setProperty(null, null));
        assertThrows(IllegalArgumentException.class, () -> System.setProperty("", ""));
    }
}
