import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class OutputTest {

    private final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    static Stream<String> provideStrings() {
        return Stream.of("Hello", "", null);
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(newOut));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(out);
    }

    @ParameterizedTest
    @MethodSource("provideStrings")
    void out(String message) {
        System.out.print(message);

        if (message == null)
            assertEquals("null", newOut.toString());
        else
            assertEquals(message, newOut.toString());
    }
}
