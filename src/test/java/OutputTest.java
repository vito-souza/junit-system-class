import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class OutputTest {

    private final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    static Stream<String> strings() {
        return Stream.of(
                "Hi!",
                "",
                null,
                "Hello, " + System.lineSeparator() + "World!");
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(newOut));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(out);
    }

    @ParameterizedTest
    @MethodSource("strings")
    void sysout(String message) {
        System.out.print(message);
        assertEquals(message == null ? "null" : message, newOut.toString());
    }

    @Test
    void lineSeparator() {
        String expectedSeparator = System.getProperty("os.name").toLowerCase().contains("win") ? "\r\n" : "\n";
        assertEquals(expectedSeparator, System.lineSeparator());
    }
}
