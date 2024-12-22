package refactoring;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public enum SystemData {
	WIN_LINE_SEPARATOR("\r\n"), UNIX_LINE_SEPARATOR("\n"), WIN_FILE_SEPARATOR("\\"),
	UNIX_FILE_SEPARATOR("/"), WIN_PATH_SEPARATOR(";"), UNIX_PATH_SEPARATOR(":"),
	DATE_FORMAT("yyyy-MM-dd HH:mm:ss"), PROPERTY_KEY("key"), PROPERTY_VALUE("value");

	public static final ByteArrayOutputStream NEW_OUT = new ByteArrayOutputStream();
	public static final PrintStream ORIGINAL_OUT = System.out;

	public static final int[] INT_ARRAY = { 0, 1, 2 };
	public static final int[] INT_DESTINATION_ARRAY = new int[3];
	public static final double[] DOUBLE_DESTINATION_ARRAY = new double[3];

	public static final Object OBJ_ONE = new Object();
	public static final Object OBJ_TWO = new Object();
	public static final Object OBJ_THREE = OBJ_ONE;
	public static final Object NULL_OBJECT = null;

	public static final String PATH_ENV = System.getenv("PATH");
	public static final String OS_NAME = System.getProperty("os.name");

	private final String value;

	SystemData(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
