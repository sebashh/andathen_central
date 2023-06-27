package nl.andathen.central.domain.galaxy.planet;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Culture;
import nl.andathen.central.domain.Species;
import nl.andathen.central.domain.resources.Resource;
import nl.andathen.central.util.IllegalOperationException;

@Entity 
@Table(name="known_planet", indexes = { @Index(name = "IDX_KNOWN_PLANET_TECHLEVEL", columnList = "technical_level"),
		  								@Index(name = "IDX_KNOWN_PLANET_POPULATION", columnList = "population")})

public class KnownPlanet extends Planet implements Serializable {
	private static final long serialVersionUID = 1915169057063985758L;

	@Column(name="population", nullable=false)
	private double population;
	
	@Column(name="description", nullable=false, columnDefinition="TEXT")
	private String description;
	
	@Column(name="access_level_techlevel", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelTechlevel;
	
	@Column(name="technical_level", nullable=true)
	@Enumerated(EnumType.STRING) 
	private TechnicalLevel technicalLevel;

	@Column(name="access_level_cultures", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelCultures;
	
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name="culture_id", nullable=true)
	private Culture culture; // Major culture present on the planet
	
	@ElementCollection(targetClass = String.class)
	@MapKeyColumn(name = "culture_name")
	@Column(name="alternate_name", nullable=true)
	@CollectionTable(name = "planet_culture", joinColumns = {@JoinColumn(name = "planet_designation", referencedColumnName = "designation")})
	private Map<Culture, String> cultureNames; // Map of names according to different cultures
	
	@ElementCollection
	@CollectionTable(name = "planet_city", 
	  joinColumns = {@JoinColumn(name = "planet_designation", referencedColumnName = "designation")})
	@Column(name = "population", nullable=true)
	private Map<String, Integer> cities; // Map of cities with number of inhabitants
	
	@Column(name="access_level_species", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelSpecies;
	
	@ElementCollection
	@CollectionTable(name = "planet_species", 
	  joinColumns = {@JoinColumn(name = "planet_designation", referencedColumnName = "designation")})
	@Column(name = "fraction", nullable=false)
	private Map<Species, Float> speciesPresent; // Map of species versus fraction
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)  
	@JoinColumn(name="planet_designation")  
	@Fetch(FetchMode.SELECT)
	private Set<Resource> resources; // The major resources characters can find on this planet
	
	@Column(name="access_level_resource", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelResources;

	@Column(name="access_level_climates", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelClimates;
	
	@ElementCollection
	@CollectionTable(name = "planet_climate", 
	  joinColumns = {@JoinColumn(name = "planet_designation", referencedColumnName = "designation")})
	@Column(name = "fraction", nullable=false)
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name="climate_name")
	private Map<Climate, Float> climates; // Climates present on planet
	
//	@Column(name="access_level_flora_fauna", nullable=false)
//	@Enumerated(EnumType.STRING) 
	@Transient
	private AccessLevel accessLevelFloraFauna;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)  
//	@JoinColumn(name="planet_designation")  
//	@Fetch(FetchMode.SELECT)
	@Transient
	private Set<Organism> floraFauna; // The flora and fauna on a planet
	
	public KnownPlanet() {
		
	}

	public void addSpecies(Species species, float fraction) throws IllegalOperationException {
		if (speciesPresent == null) {
			speciesPresent = new HashMap<Species, Float>();
		}
		else if (speciesPresent.containsKey(species)) {
			throw new IllegalOperationException("Species already present in map.");
		}
		speciesPresent.put(species, fraction);
	}

	public void addClimate(Climate climate, float fraction) throws IllegalOperationException {
		if (fraction > 1.0f) {
			throw new IllegalOperationException("Fraction too high!");
		}
		if (fraction <= 0.0f) {
			throw new IllegalOperationException("Fraction cannot be negative!");
		}
		if (climates == null) {
			climates = new EnumMap<Climate, Float>(Climate.class);
			climates.put(Climate.OCEAN, 100f);
		}
		else if (climates.containsKey(climate)) {
			throw new IllegalOperationException("Climate already present in map.");
		}
		float total = 0;
		Set<Climate> keys = climates.keySet();
		for (Climate key: keys) {
			if (key != Climate.OCEAN) {
				total += climates.get(key);
			}
		}
		if ((total + fraction) > 1.0f) {
			throw new IllegalOperationException("Total fraction of climates would become more than 100%.");
		}
		float ocean = 1.0f - total;
		climates.remove(Climate.OCEAN);
		climates.put(climate,  fraction);
		climates.put(Climate.OCEAN, ocean);
	}
	
	public void deleteClimate(Climate climate) throws IllegalOperationException {
		if ((climates == null) ||  (!climates.containsKey(climate))) {
			throw new IllegalOperationException("Climate not present in map.");
		}
		if (climate == Climate.OCEAN) {
			throw new IllegalOperationException("You cannot remove the ocean.");
		}
		float total = 0;
		Set<Climate> keys = climates.keySet();
		for (Climate key: keys) {
			if (key != Climate.OCEAN) {
				total += climates.get(key);
			}
		}
		float ocean = 1.0f - total;
		climates.remove(climate);
		climates.put(Climate.OCEAN, ocean);
	}
	
	public void changeSpecies(Species species, float fraction) throws IllegalOperationException {
		if ((speciesPresent == null) || (!speciesPresent.containsKey(species))) {
			throw new IllegalOperationException("Species not present in map.");
		}
		else {
			speciesPresent.put(species, fraction);
		}
	}

	public void deleteSpecies(Species species) throws IllegalOperationException {
		if ((speciesPresent == null) ||  (!speciesPresent.containsKey(species))) {
			throw new IllegalOperationException("Species not present in map.");
		}
		else {
			speciesPresent.remove(species);
		}
	}

	public float getFraction(Species species) {
		if (speciesPresent == null) {
			return 0f;
		}
		else {
			Float result = speciesPresent.get(species);
			if (result == null) {
				return 0f;
			}
			else {
				return result;
			}
		}
	}
	
	public Map<Climate, Float> getClimates() {
		Map<Climate, Float> result = new HashMap<>();
		Set<Climate> keys = climates.keySet();
		for (Climate key: keys) {
			result.put(key,  climates.get(key));
		}
		return result;
	}
	
	public Map<String, Integer> getCities() {
		Map<String, Integer> result = new HashMap<>();
		Set<String> keys = cities.keySet();
		for (String key: keys) {
			result.put(key,  cities.get(key));
		}
		return result;
	}
	
	public int getCity(String city) {
		return cities.get(city);
	}
	
	public int addCity(String city, Integer population) {
		return cities.put(city, population);
	}
	
	public int removeCity(String city) {
		return cities.remove(city);
	}

	// TO DO Check!
	public String getName(Culture culture) {
		if (cultureNames == null) {
			return null;
		}
		else {
			return cultureNames.get(culture);
		}
	}

	// TODO Check whether culture is known
	public String setName(Culture culture, String name) {
		if (cultureNames == null) {
			cultureNames = new HashMap<>();
		}
		return cultureNames.put(culture, name);
	}
	
	public boolean addOrganism(Organism organism) {
		return floraFauna.add(organism);
	}
	
	public boolean removeOrganism(Organism organism) {
		return floraFauna.remove(organism);
	}
	
	public Set<Organism> getOrganisms() {
		Set<Organism> result = new HashSet<>();
		result.addAll(floraFauna);
		return result;
	}

	public double getPopulation() {
		return population;
	}

	public void setPopulation(double population) {
		this.population = population;
	}
	
	public TechnicalLevel getTechnicalLevel() {
		return technicalLevel;
	}

	public void setTechnicalLevel(TechnicalLevel technicalLevel) {
		this.technicalLevel = technicalLevel;
	}

	public AccessLevel getAccessLevelTechlevel() {
		return accessLevelTechlevel;
	}

	public void setAccessLevelTechlevel(AccessLevel accessLevelTechlevel) {
		this.accessLevelTechlevel = accessLevelTechlevel;
	}

	public AccessLevel getAccessLevelClimates() {
		return accessLevelClimates;
	}

	public void setAccessLevelClimates(AccessLevel accessLevelClimates) {
		this.accessLevelClimates = accessLevelClimates;
	}

	public AccessLevel getAccessLevelCultures() {
		return accessLevelCultures;
	}

	public void setAccessLevelCultures(AccessLevel accessLevelCultures) {
		this.accessLevelCultures = accessLevelCultures;
	}

	public AccessLevel getAccessLevelSpecies() {
		return accessLevelSpecies;
	}

	public void setAccessLevelSpecies(AccessLevel accessLevelSpecies) {
		this.accessLevelSpecies = accessLevelSpecies;
	}

	public AccessLevel getAccessLevelFloraFauna() {
		return accessLevelFloraFauna;
	}

	public void setAccessLevelFloraFauna(AccessLevel accessLevelFloraFauna) {
		this.accessLevelFloraFauna = accessLevelFloraFauna;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Culture getCulture() {
		return culture;
	}

	public void setCulture(Culture culture) {
		this.culture = culture;
	}
}
