package nl.andathen.central.domain.resources;

public enum Scarcity { COMMON("Can be found in so many places it is essentially unlimited. Price depends on how hard it is to refine, not on scarcity."), 
							UNCOMMON("The material can be found in many places, but is not easy to retrieve and/ or needs extensive refining before it can be used."), 
							RARE("The material is found in many places, but will not be easy to reach and retrieve and needs extensive refining before it can be used."), 
							RESTRICTED("A rare compound with properties causing it to be forbidden or subject to licensing in most societies."), 
							SPECIAL("A material or compound which cannot be created from other products and (usually) can only be found in one place. Often restricted as well, though that is not always the case."), 
							UNIQUE("Something which was found once and has very special properties. It is unknown how to create this.");
	private final String description;

	private Scarcity(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getScarcity() {
		return this.toString().substring(0,1) + this.toString().substring(1).toLowerCase();
	}
	
	public static Scarcity parse(String description) {
		if (description.equals("UNCOMMON")) {
			return Scarcity.UNCOMMON;
		}
		if (description.equals("COMMON")) {
			return Scarcity.COMMON;
		}
		if (description.equals("RARE")) {
			return Scarcity.RARE;
		}
		if (description.equals("RESTRICTED")) {
			return Scarcity.RESTRICTED;
		}
		if (description.equals("SPECIAL")) {
			return Scarcity.SPECIAL;
		}
		if (description.equals("UNIQUE")) {
			return Scarcity.UNIQUE;
		}
		return null;
	}
}
