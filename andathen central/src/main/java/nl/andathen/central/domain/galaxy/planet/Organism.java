package nl.andathen.central.domain.galaxy.planet;

import nl.andathen.central.domain.AccessLevel;

public abstract class Organism {
	private String structuredName;
	private String name;
	private OrganismFamily organismFamily;
	private PlanetType preferredPlanetType;
	private Climate preferredClimate;
	private AccessLevel accessLevel;

	public Organism(String structuredName, String name, Climate preferredClimate, OrganismFamily organismFamily, PlanetType preferredPlanetType, AccessLevel accessLevel) {
		super();
		this.structuredName = structuredName;
		this.name = name;
		this.organismFamily = organismFamily;
		this.accessLevel = accessLevel;
		this.preferredPlanetType = preferredPlanetType;
		this.preferredClimate = preferredClimate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStructuredName() {
		return structuredName;
	}

	public void setStructuredName(String structuredName) {
		this.structuredName = structuredName;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public OrganismFamily getOrganismFamily() {
		return organismFamily;
	}

	public void setOrganismFamily(OrganismFamily organismFamily) {
		this.organismFamily = organismFamily;
	}

	public PlanetType getPreferredPlanetType() {
		return preferredPlanetType;
	}

	public void setPreferredPlanetType(PlanetType preferredPlanetType) {
		this.preferredPlanetType = preferredPlanetType;
	}

	public Climate getPreferredClimate() {
		return preferredClimate;
	}

	public void setPreferredClimate(Climate preferredClimate) {
		this.preferredClimate = preferredClimate;
	}
}
