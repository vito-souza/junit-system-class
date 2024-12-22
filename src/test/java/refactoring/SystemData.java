package refactoring;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public enum SystemData {
	WIN_LINE_SEPARATOR("\r\n"), UNIX_LINE_SEPARATOR("\n"), WIN_FILE_SEPARATOR("\\"), UNIX_FILE_SEPARATOR("/"),
	WIN_PATH_SEPARATOR(";"), UNIX_PATH_SEPARATOR(":"), DATE_FORMAT("yyyy-MM-dd HH:mm:ss"), PROPERTY_KEY("key"),
	PROPERTY_VALUE("value"), PATH_ENV(System.getenv("PATH")), OS_NAME(System.getProperty("os.name"));

	static final ByteArrayOutputStream NEW_OUT = new ByteArrayOutputStream();
	static final PrintStream ORIGINAL_OUT = System.out;

	static final int[] INT_ARRAY = { 0, 1, 2 };
	static final int[] INT_DESTINATION_ARRAY = new int[3];
	static final double[] DOUBLE_DESTINATION_ARRAY = new double[3];

	static final Object OBJ_ONE = new Object();
	static final Object OBJ_TWO = new Object();
	static final Object OBJ_THREE = OBJ_ONE;
	static final Object NULL_OBJECT = null;

	private final String value;

	SystemData(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
