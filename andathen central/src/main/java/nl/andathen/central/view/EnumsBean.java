package nl.andathen.central.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Gender;
import nl.andathen.central.domain.Intelligence;
import nl.andathen.central.domain.LanguageType;
import nl.andathen.central.domain.SpeciesCouncilRelation;
import nl.andathen.central.domain.character.SkillCategory;
import nl.andathen.central.domain.council.ShipType;
import nl.andathen.central.domain.galaxy.LuminosityClass;
import nl.andathen.central.domain.galaxy.StarType;
import nl.andathen.central.domain.galaxy.planet.Climate;
import nl.andathen.central.domain.galaxy.planet.OrganismType;
import nl.andathen.central.domain.galaxy.planet.PlanetType;
import nl.andathen.central.domain.galaxy.planet.TechnicalLevel;
import nl.andathen.central.domain.resources.Scarcity;

@Named
@ApplicationScoped
public class EnumsBean {
	private AccessLevel[] accessLevels;
	private LanguageType[] languageTypes;
	private Scarcity[] scarcities;
	private Gender[] genders;
	private Intelligence[] intelligenceLevels;
	private SkillCategory[] skillCategories;
	private LuminosityClass[] luminosityClasses;
	private StarType[] starTypes;
	private OrganismType[] organismTypes;
	private PlanetType[] planetTypes;
	private PlanetType.Habitability[] habitabilities;
	private TechnicalLevel[] technicalLevels;
	private Climate[] climates;
	private SpeciesCouncilRelation[] relations;
	private ShipType[] shipTypes;
	
	@PostConstruct
	public void init() {
		accessLevels = AccessLevel.values();
		languageTypes = LanguageType.values();
		scarcities = Scarcity.values();
		genders = Gender.values();
		intelligenceLevels = Intelligence.values();
		skillCategories = SkillCategory.values();
		luminosityClasses = LuminosityClass.values();
		starTypes = StarType.values();
		organismTypes = OrganismType.values();
		planetTypes = PlanetType.values();
		habitabilities = PlanetType.Habitability.values();
		technicalLevels = TechnicalLevel.values();
		climates = Climate.values();
		relations = SpeciesCouncilRelation.values();
		shipTypes = ShipType.values();
	}
	
	public AccessLevel[] getAccessLevels() {
		return accessLevels;
	}
	
	public LanguageType[] getLanguageTypes() {
		return languageTypes;
	}
	
	public Scarcity[] getScarcities() {
		return scarcities;
	}

	public Gender[] getGenders() {
		return genders;
	}

	public Intelligence[] getIntelligenceLevels() {
		return intelligenceLevels;
	}

	public SkillCategory[] getSkillCategories() {
		return skillCategories;
	}

	public LuminosityClass[] getLuminosityClasses() {
		return luminosityClasses;
	}

	public StarType[] getStarTypes() {
		return starTypes;
	}

	public OrganismType[] getOrganismTypes() {
		return organismTypes;
	}

	public PlanetType[] getPlanetTypes() {
		return planetTypes;
	}

	public TechnicalLevel[] getTechnicalLevels() {
		return technicalLevels;
	}

	public Climate[] getClimates() {
		return climates;
	}

	public SpeciesCouncilRelation[] getRelations() {
		return relations;
	}

	public PlanetType.Habitability[] getHabitabilities() {
		return habitabilities;
	}

	public ShipType[] getShipTypes() {
		return shipTypes;
	}
}
