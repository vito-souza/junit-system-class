package vitor.dev;

import java.io.File;

public class Main {
	public static void main(String[] args) throws Exception {
		File file = File.createTempFile("temp", ".tmp");
		System.out.println(file.getAbsolutePath());
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("file.separator"));
	}
}