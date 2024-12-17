package refactoring;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public enum SystemData {
	WIN_LINE_SEPARATOR("\r\n"),
	UNIX_LINE_SEPARATOR("\n"),
	WIN_FILE_SEPARATOR("\\"),
	UNIX_FILE_SEPARATOR("/"),
	WIN_PATH_SEPARATOR(";"),
	UNIX_PATH_SEPARATOR(":"),
	DATE_FORMAT("yyyy-MM-dd HH:mm:ss"),
	PROPERTY_KEY("key"),
	PROPERTY_VALUE("value"),

	NULL(null);

	public static final ByteArrayOutputStream NEW_OUT = new ByteArrayOutputStream();
	public static final PrintStream ORIGINAL_OUT = System.out;

	private final String value;

	SystemData(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
