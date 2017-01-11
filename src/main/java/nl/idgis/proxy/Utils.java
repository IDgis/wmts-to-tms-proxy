package nl.idgis.proxy;

import java.io.File;

public class Utils {

	public static boolean isValidVersion(String version) {
		if (version == null) {
			return false;
		}

		return version.matches("[0-9]\\.[0-9]\\.[0-9]");
	}

	public static boolean isInteger(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			if (Character.digit(s.charAt(i), 10) < 0) {
				return false;
			}
		}

		return true;
	}
	
	public static int yMax(int z) {
		if (z < 0) {
			throw new IllegalArgumentException(String.format("%d should be >= 0", z));
		}
		
		return (1 << z) - 1;
	}
	
	public static int wmtsRow(int y, int z) {
		if (y < 0) {
			throw new IllegalArgumentException(String.format("%d should be >= 0", y));
		}

		final int yMax = yMax(z);
		return yMax - y;
	}

	public static boolean hasExtension(File file, String extension) {
		final String path = file.getPath();
		return path.endsWith(String.format(".%s", extension));
	}
	
}
