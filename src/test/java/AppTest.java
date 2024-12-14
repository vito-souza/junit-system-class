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

        Assertions.assertArrayEquals(new int[] { 0, 1, 2 }, destination);
    }

    @Test
    void clearProperty() {
        System.setProperty("blah", "blech");
        Assertions.assertEquals("blech", System.getProperty("blah"));

        System.clearProperty("blah");
        Assertions.assertNull(System.getProperty("blah"));

        Assertions.assertThrows(NullPointerException.class, () -> System.clearProperty(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> System.clearProperty(""));
    }

    @ParameterizedTest
    @ValueSource(strings = { "PATH", "foo" })
    void getenv(String env) {
        if ("foo".equals(env))
            Assertions.assertNull(System.getenv(env));
        else
            Assertions.assertNotNull(System.getenv(env));
    }
}
