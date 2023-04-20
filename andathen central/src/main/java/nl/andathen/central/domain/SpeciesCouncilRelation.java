package nl.andathen.central.domain;

public enum SpeciesCouncilRelation { MEMBER("Species is a full member of the Council"), 
							ALLIED("While not a full member the species either applied for membership or can be considered a member except in name."), 
							RELAXED("Species is not a member, but has extensive trade with planets in Council Space and follows all trade rules."), 
							PROTECTED("The species is not yet developed enough to introduce them to the interspecies situations. The Council sees to it that these cultures can develop without interference."), 
							POLITE("As this species does not want to comply with trade rules or has no desire to trade, there is not much of a political situation here. Both sides drink tea once in a while to make sure there will not be a conflict."), 
							TENSE("Relations between the Council and this species are tense. They are either talking or politely disagreeing, the threat of armed conflict is never far away and skirmished do occur once in a while."), 
							QUARAINTAINEED("Relations between the Council and this species have gone beyond tense. The power distribution however is so lobsided that the Council effectively blocks the species from causing troubles by just locking them inside their system(s.)"), 
							WAR("There is an outright war between the Council and this species. Fleets are moving!"), 
							OUTSIDE("No relations to speak of. While the Council knows about their existence, the homeplanet of the species just is too far away to do anything with that knowledge. This is true for many planets with a few hundred parsec of the borders."), 
							UNKNOWN("The council does not even know about the existence of this species.");
	private final String description;

	private SpeciesCouncilRelation(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static SpeciesCouncilRelation parse(String description) {
		if (description.equals("MEMBER")) {
			return SpeciesCouncilRelation.MEMBER;
		}
		if (description.equals("ALLIED")) {
			return SpeciesCouncilRelation.ALLIED;
		}
		if (description.equals("RELAXED")) {
			return SpeciesCouncilRelation.RELAXED;
		}
		if (description.equals("PROTECTED")) {
			return SpeciesCouncilRelation.PROTECTED;
		}
		if (description.equals("TENSE")) {
			return SpeciesCouncilRelation.TENSE;
		}
		if (description.equals("QUARAINTAINEED")) {
			return SpeciesCouncilRelation.QUARAINTAINEED;
		}
		if (description.equals("WAR")) {
			return SpeciesCouncilRelation.WAR;
		}
		if (description.equals("POLITE")) {
			return SpeciesCouncilRelation.POLITE;
		}
		if (description.equals("OUTSIDE")) {
			return SpeciesCouncilRelation.OUTSIDE;
		}
		if (description.equals("UNKNOWN")) {
			return SpeciesCouncilRelation.UNKNOWN;
		}
		return null;
	}
}
