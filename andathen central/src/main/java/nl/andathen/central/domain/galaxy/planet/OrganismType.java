package nl.andathen.central.domain.galaxy.planet;

public enum OrganismType { ANIMAL("Organism has no cell walls, only mebranes, and consists of multiple cells working together. Might or might not be mobile."),
							PLANT("Organism has cell walls. It might be single-celled (plankton) or multi-celled. Often performs fotosynthesis"), 
							BACTERIA("Single cell organism which needs other organisms to survive. Can be parasitic but not neccesarily so."), 
							VIRUS("Questionable whether this is alive. Basically DNA or RNA with some protein mantle. Cannot reproduce, invades cells to have them do that."), 
							FUNGUS("Close to a plant, but not quit."), 
							OTHER("Organism is too far from gaia standards to classify.");

	private final String description;

	private OrganismType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static OrganismType parse(String description) {
		if (description.equals("ANIMAL")) {
			return OrganismType.ANIMAL;
		}
		if (description.equals("PLANT")) {
			return OrganismType.PLANT;
		}
		if (description.equals("BACTERIA")) {
			return OrganismType.BACTERIA;
		}
		if (description.equals("VIRUS")) {
			return OrganismType.VIRUS;
		}
		if (description.equals("FUNGUS")) {
			return OrganismType.FUNGUS;
		}
		if (description.equals("OTHER")) {
			return OrganismType.OTHER;
		}
		return null;
	}
}
