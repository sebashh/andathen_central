package nl.andathen.central.util;

public class StringUtil {
	public static final int SHORT_DESCRIPTION_LENGTH = 80;
	
	public static String getShortDescription(String description) {
		if (description == null) {
			return "";
		}
		else {
			if (description.length() <= SHORT_DESCRIPTION_LENGTH) {
				return description;
			}
			else {
				StringBuilder result = new StringBuilder(description.substring(0,SHORT_DESCRIPTION_LENGTH));
				if (description.length() > SHORT_DESCRIPTION_LENGTH) {
					result.append("....");
				}
				return new String(result);
			}
		}
	}
}
