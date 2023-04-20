package nl.andathen.central.domain;

public enum LanguageType { LIVING("Language actively spoken by more than 10 people"), 
					 EXTINCT("Language used neither in daily situations nor in scientific situations"), 
					 CONSTRUCTED("Language has been created for a specific purpose"), 
					 ANCIENT("Language is not spoken in daily life anymore but has significance in texts");
	private String description;
	
	public String getType() {
		return this.toString().substring(0,1) + this.toString().substring(1).toLowerCase();
	}
	
	private LanguageType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static LanguageType parse(String description) {
		if (description.equals("LIVING")) {
			return LanguageType.LIVING;
		}
		if (description.equals("EXTINCT")) {
			return LanguageType.EXTINCT;
		}
		if (description.equals("CONSTRUCTED")) {
			return LanguageType.CONSTRUCTED;
		}
		if (description.equals("ANCIENT")) {
			return LanguageType.ANCIENT;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return name().substring(0,1) + name().substring(1).toLowerCase();
	}
}
