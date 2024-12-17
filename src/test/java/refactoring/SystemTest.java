package refactoring;

import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class SystemTest {

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(SystemData.NEW_OUT));
	}

	@AfterEach
	public void tearDown() {
		System.setOut(SystemData.ORIGINAL_OUT);
	}
}
