package nl.andathen.central.util;

import java.sql.Timestamp;

public class FileUtil {
	public static String timestampFilename(String filename) {
		return filename.substring(0,filename.indexOf(".")) 
				+ "-"
				+ ((new Timestamp(System.currentTimeMillis()).toString().replaceAll(" ", "-"))).replaceAll(":", "-")
				+ filename.substring(filename.indexOf("."));
	}
	
	public static boolean isImageFile(String filename) {
		if (filename.toLowerCase().endsWith(".gif")) return true;
		if (filename.toLowerCase().endsWith(".png")) return true;
		if (filename.toLowerCase().endsWith(".jpg")) return true;
		return false;
	}
}
