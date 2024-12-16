import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.ref.WeakReference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AppTest {

    private static final String KEY = "blah";
    private static final String VALUE = "blech";

    @Test
    void arraycopy() {
        int[] source = { 0, 1, 2 };
        int[] destination = new int[3];

        System.arraycopy(source, 0, destination, 0, source.length);

        assertArrayEquals(new int[] { 0, 1, 2 }, destination);
    }

    @Test
    void clearProperty() {
        System.setProperty(KEY, VALUE);
        Assertions.assertEquals(VALUE, System.getProperty(KEY));

        System.clearProperty(KEY);
        assertNull(System.getProperty(KEY));

        assertThrows(NullPointerException.class, () -> System.clearProperty(null));
        assertThrows(IllegalArgumentException.class, () -> System.clearProperty(""));
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
    void getProperty() {
        System.setProperty(KEY, VALUE);
        assertEquals(VALUE, System.getProperty(KEY));

        assertThrows(NullPointerException.class, () -> System.getProperty(null));
        assertThrows(IllegalArgumentException.class, () -> System.getProperty(""));
    }

    @Test
    void setProperty() {
        System.setProperty(KEY, VALUE);
        assertEquals(VALUE, System.getProperty(KEY));

        assertThrows(NullPointerException.class, () -> System.setProperty(null, null));
        assertThrows(IllegalArgumentException.class, () -> System.setProperty("", ""));
    }

    @Test
    void lineSeparator() {
        String expectedSeparator = System.getProperty("os.name").toLowerCase().contains("win") ? "\r\n" : "\n";
        assertEquals(expectedSeparator, System.lineSeparator());
    }

    @Test
    void gc() {
        Object obj = new Object();
        WeakReference<Object> weakRef = new WeakReference<>(obj);

        obj = null;

        System.gc();

        assertNull(weakRef.get());
    }
}
