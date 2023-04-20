package nl.andathen.central.domain.character;

public enum SkillCategory { COMBAT(1, "Knowing how to use close combat weapons."), 
							GUNS(2, "Long-range weaponry. This also includes the ancient bow."), 
							PROTECTION(3, "Mechanical or force-field protections agains getting injured. Might work agains radiation."), 
							MANOUVRES(4, "Special manouvres which might give one an advantage in battle (or just avoid the battle altogether.)"), 
							COMPUTER(5, "Skills having to do with retrieving and manipulating information systems"), 
							NAVIGATION(6, "Knowing how to get from one place to another."), 
							ENGINEERING(7, "Creating and repairing mechanical or electronic items."), 
							SCIENCE(8, "Creating or manipulating biology or the forces of nature."), 
							MEDICAL(9, "Healing, getting rid of poisons and diseases, or just keeping someone alive until help arrives."), 
							PSYCHIC(10, "Powers of the mind, or how to resist them."), 
							DIPLOMACY(11, "Skills which come in handy to make others see your point."), 
							ROGUE(12, "All the sneaky or otherwise frowned upon skills."), 
							MISCELLANEOUS(13, "Whatever skills we could not fit into another category.");
	private final int index;
	private final String description;

	private SkillCategory(int index, String description) {
		this.description = description;
		this.index = index;
	}
	
	public String getCategory() {
		return this.toString().substring(0,1) + this.toString().substring(1).toLowerCase();
	}
	
	public String getDescription() {
		return description;
	}	
	
	public int getIndex() {
		return index;
	}

	public static SkillCategory parse(String description) {
		if (description.equals("COMBAT")) {
			return SkillCategory.COMBAT;
		}
		if (description.equals("GUNS")) {
			return SkillCategory.GUNS;
		}
		if (description.equals("PROTECTION")) {
			return SkillCategory.PROTECTION;
		}
		if (description.equals("MANOUVRES")) {
			return SkillCategory.MANOUVRES;
		}
		if (description.equals("ROGUE")) {
			return SkillCategory.ROGUE;
		}
		if (description.equals("MEDICAL")) {
			return SkillCategory.MEDICAL;
		}
		if (description.equals("COMPUTER")) {
			return SkillCategory.COMPUTER;
		}
		if (description.equals("ENGINEERING")) {
			return SkillCategory.ENGINEERING;
		}
		if (description.equals("NAVIGATION")) {
			return SkillCategory.NAVIGATION;
		}
		if (description.equals("SCIENCE")) {
			return SkillCategory.SCIENCE;
		}
		if (description.equals("PSYCHIC")) {
			return SkillCategory.PSYCHIC;
		}
		if (description.equals("DIPLOMACY")) {
			return SkillCategory.DIPLOMACY;
		}
		if (description.equals("MISCELLANEOUS")) {
			return SkillCategory.MISCELLANEOUS;
		}
		return null;
	}
}
