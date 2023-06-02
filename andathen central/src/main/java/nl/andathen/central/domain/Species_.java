package nl.andathen.central.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.andathen.central.domain.character.Skill;
import nl.andathen.central.domain.galaxy.planet.Climate;
import nl.andathen.central.domain.galaxy.planet.PlanetType;
import nl.andathen.central.domain.galaxy.planet.TechnicalLevel;

@Generated(value="Dali", date="2022-07-05T13:24:29.637+0200")
@StaticMetamodel(Species.class)
public class Species_ {
	public static volatile SingularAttribute<Species, Long> id;
	public static volatile SingularAttribute<Species, String> name;
	public static volatile SingularAttribute<Species, String> description;
	public static volatile SingularAttribute<Species, String> playerNotes;
	public static volatile SingularAttribute<Species, Intelligence> intelligence;
	public static volatile SingularAttribute<Species, AccessLevel> accessLevelIntelligence;
	public static volatile SingularAttribute<Species, AccessLevel> accessLevelTechlevel;
	public static volatile SingularAttribute<Species, TechnicalLevel> technicalLevel;
	public static volatile SingularAttribute<Species, PlanetType> preferredPlanetType;
	public static volatile SingularAttribute<Species, Climate> preferredClimate;
	public static volatile SingularAttribute<Species, AccessLevel> accessLevelClimate;
	public static volatile SingularAttribute<Species, AccessLevel> accessLevelOrigins;
	public static volatile SingularAttribute<Species, Boolean> playable;
	public static volatile SingularAttribute<Species, Boolean> inCouncilSpace;
	public static volatile SingularAttribute<Species, SpeciesCouncilRelation> councilRelations;
	public static volatile MapAttribute<Species, Skill, Integer> skillModifiers;
	public static volatile SingularAttribute<Species, AccessLevel> accessLevelModifiers;
	public static volatile SingularAttribute<Species, AccessLevel> accessLevel;
	public static volatile SingularAttribute<Species, AccessLevel> accessLevelImage;
}