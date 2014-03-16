package me.inplex.jomapat.extra;

import java.io.File;

public class Util {

	public static String getDataPath() {
		String os = System.getProperty("os.name").toUpperCase();
		String dir = "";
		if (os.contains("WIN"))
			dir = System.getenv("APPDATA");
		else if (os.contains("MAC"))
			dir = System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support";
		else if (os.contains("NUX") || os.contains("NIX"))
			dir = System.getProperty("user.home");
		else System.getProperty("user.dir");
		dir +=  File.separator + "Jomapat" + File.separator;
		return dir;
	}

}