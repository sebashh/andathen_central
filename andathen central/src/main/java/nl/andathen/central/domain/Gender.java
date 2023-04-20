package nl.andathen.central.domain;

public enum Gender { M("Male", "Gender producing the smaller reproductive cell"), 
					 F("Female", "Gender producing the larger reproductive cell"), 
					 O("Other", "Catch-all for situations where the reproductive structure is unclear"), 
					 U("Unknown", "We have'nt got the foggiest");
	private String name;
	private String description;
	
	private Gender(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
	public static Gender parse(String description) {
		if (description.toUpperCase().equals("M")) {
			return Gender.M;
		}
		if (description.toUpperCase().equals("F")) {
			return Gender.F;
		}
		if (description.toUpperCase().equals("O")) {
			return Gender.O;
		}
		if (description.toUpperCase().equals("U")) {
			return Gender.U;
		}
		return null;
	}
}
