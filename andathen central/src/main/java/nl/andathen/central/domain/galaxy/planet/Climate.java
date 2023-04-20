package nl.andathen.central.domain.galaxy.planet;

public enum Climate { SUBARCTIC("Temperatures are so low no life is possible outside artifical domes (or not even there.)", 0.0d, 0.0d),
							ARCTIC("Freezing most of the year or all-year; indiginous life or some gaia-adapted supported if there is a breathable atmosphere.",0.1d, 0.1d), 
							TEMPERATE("Temperatures and precipitation vary throughout a year in a way fully compatible with life.", 1.0d, 1.0d), 
							MARSHY("Temperatures are stable throughout the year and good for life. Humidity is always very high.", 0.5d, 0.5d), 
							SUBTROPICAL("Temperatures stay high during the year. Precipitation is plenty but very concentrated in season or time of day.", 0.8d, 0.4d),
							TROPICAL("Temperatures and precipitation vary throughout the day but stay pretty high. Life is possible here, but tool-users are less likely.", 1.0d, 0.2d),
							DESERT("Low humidy and high day temperatures make life hard but not impossible.", 0.1d, 0.1d), 
							FIERY("Very high day temperatures make for a situation where some life can be supported, but most of it will be indiginous.", 0.02d, 0.0d), 
							SUPERFIERY("Temperatures are so high no life can exist here. Short-time occupation in special artifical domes is sometimes an option.", 0.0d, 0.0d), 
							CHEMICAL("Atmosphere is highly toxic and / or caustic. Life can not be supported, except maybe for some very simple indiginous organisms.", 0.0d, 0.0d), 
							OCEAN("The region is covered by a deep body of water, but temperature and atmosphere are suitable for supporting life.", 0.3d, 0.01d);

	private final String description;
	private final double maxSupported; // Fraction of life which can be supported compared to gaia class planets
	private final double toolUsers; // Chance of tool-using societies compared to gaia class planets

	private Climate(String description, double maxSupported, double toolUsers) {
		this.description = description;
		this.maxSupported = maxSupported;
		this.toolUsers = toolUsers;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Climate parse(String description) {
		if (description.equals("SUBARCTIC")) {
			return Climate.SUBARCTIC;
		}
		if (description.equals("ARCTIC")) {
			return Climate.ARCTIC;
		}
		if (description.equals("TEMPERATE")) {
			return Climate.TEMPERATE;
		}
		if (description.equals("MARSHY")) {
			return Climate.MARSHY;
		}
		if (description.equals("SUBTROPICAL")) {
			return Climate.SUBTROPICAL;
		}
		if (description.equals("DESERT")) {
			return Climate.DESERT;
		}
		if (description.equals("FIERY")) {
			return Climate.FIERY;
		}
		if (description.equals("SUPERFIERY")) {
			return Climate.SUPERFIERY;
		}
		if (description.equals("CHEMICAL")) {
			return Climate.CHEMICAL;
		}
		if (description.equals("OCEAN")) {
			return Climate.OCEAN;
		}
		if (description.equals("TROPICAL")) {
			return Climate.TROPICAL;
		}
		return null;
	}
	
	public double getMaxSupported() {
		return maxSupported;
	}

	public double getToolUsers() {
		return toolUsers;
	}
}
