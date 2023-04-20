package nl.andathen.central.domain;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import nl.andathen.central.domain.character.Skill;
import nl.andathen.central.domain.galaxy.planet.Climate;
import nl.andathen.central.domain.galaxy.planet.Planet;
import nl.andathen.central.domain.galaxy.planet.PlanetType;
import nl.andathen.central.domain.galaxy.planet.TechnicalLevel;
import nl.andathen.central.util.IImageProvider;
import nl.andathen.central.util.StringUtil;

@Indexed
@Entity  
@Table(name="Species", indexes = { @Index(name = "IDX_SPECIES_ACCESS_LEVEL", columnList = "access_level"),
								   @Index(name = "IDX_SPECIES_NAME", columnList = "name"),
								   @Index(name = "IDX_SPECIES_ACCESS_LEVEL_IMAGE", columnList = "access_level_image"),
								   @Index(name = "IDX_SPECIES_ACCESS_LEVEL_MODIFIERS", columnList = "access_level_modifiers"),
								   @Index(name = "IDX_SPECIES_ACCESS_LEVEL_TECHLEVEL", columnList = "access_level_techlevel"),
								   @Index(name = "IDX_SPECIES_ACCESS_LEVEL_ORIGINS", columnList = "access_level_origins"),
								   @Index(name = "IDX_SPECIES_COUNCIL_RELATIONS", columnList = "council_relations"),
								   @Index(name = "IDX_SPECIES_ACCESS_LEVEL_IMAGE", columnList = "access_level_image"),
								   @Index(name = "IDX_SPECIES_PREFERRED_PLANET_TYPE", columnList = "preferred_planet_type"),
								   @Index(name = "IDX_SPECIES_PREFERRED_CLIMATE", columnList = "preferred_climate"),
								   @Index(name = "IDX_SPECIES_TECHNICAL_LEVEL", columnList = "technical_level"),
//								   @Index(name = "IDX_SPECIES_ORIGINS", columnList = "planet_of_origins"),
								   @Index(name = "IDX_SPECIES_INTELLIGENCE", columnList = "intelligence")})

//@AttributeOverrides({  
//    @AttributeOverride(name="planetOfOrigin", column=@Column(name="planet_of_origin"))
//})  

public class Species implements Comparable<Species>, IImageProvider, Serializable {
	private static final long serialVersionUID = 3635425242420464732L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false, unique=true)
	private String name;
	@Column(name="description", nullable=false, columnDefinition="TEXT")
	@Field
	private String description;
	
	@Column(name="player_notes", nullable=true, columnDefinition="TEXT")
	@Field
	private String playerNotes;
	
	@Column(name="intelligence", nullable=false)
	@Enumerated(EnumType.STRING) 
	private Intelligence intelligence; // The common level of intelligence of a species. This does not preclude individuals having it lower or higher.
	
	@Column(name="access_level_intelligence", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelIntelligence;
	
	@Column(name="access_level_techlevel", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelTechlevel;
	
	@Column(name="technical_level", nullable=true)
	@Enumerated(EnumType.STRING) 
	private TechnicalLevel technicalLevel;
	
	@Column(name="preferred_planet_type", nullable=true)
	@Enumerated(EnumType.STRING) 
	private PlanetType preferredPlanetType;
	
	@Column(name="preferred_climate", nullable=true)
	@Enumerated(EnumType.STRING) 
	private Climate preferredClimate;
	
	@Column(name="access_level_climate", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelClimate;
	
	//@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="planet_of_origins", nullable=true)
	@Transient
	private Planet planetOfOrigin;
	
	@Column(name="access_level_origins", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelOrigins;
	
	@Column(name="playable", nullable=false)
	private boolean playable;
	
	@Column(name="in_council_space", nullable=false)
	private boolean inCouncilSpace;
	
	@Column(name = "council_relations", nullable=false)
	@Enumerated(EnumType.STRING) 
	private SpeciesCouncilRelation councilRelations;
	
	@ElementCollection
	@CollectionTable(name = "species_skill_modifier", 
	  joinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")})
	@MapKeyColumn(name = "skill")
	@Column(name = "modifier", nullable=true)
	private Map<Skill, Integer> skillModifiers;
	
	@Column(name="access_level_modifiers", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelModifiers;
	
	@Column(name="access_level", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevel;
	
	@Lob
	@Column(name="image", nullable=true, columnDefinition="MEDIUMBLOB")
	private BufferedImage image;
	@Column(name="access_level_image", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevelImage;
	
	//@ManyToOne(cascade=CascadeType.ALL) 
	//@JoinColumn(name="source", nullable=true)
	@Transient
	private Source source;

	public Species(Long id, String name, String description, String playerNotes, Intelligence intelligence, 
			AccessLevel accessLevelIntelligence, AccessLevel accessLevelTechlevel, TechnicalLevel technicalLevel, 
			PlanetType preferredPlanetType, Climate preferredClimate, AccessLevel accessLevelClimate,
			Planet planetOfOrigin, AccessLevel accessLevelOrigins, boolean playable, boolean inCouncilSpace,
			SpeciesCouncilRelation councilRelations, Map<Skill, Integer> skillModifiers,
			AccessLevel accessLevelModifiers, AccessLevel accessLevel, BufferedImage image, AccessLevel accessLevelImage,
			Source source) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.intelligence = intelligence;
		this.accessLevelTechlevel = accessLevelTechlevel;
		this.technicalLevel = technicalLevel;
		this.preferredPlanetType = preferredPlanetType;
		this.preferredClimate = preferredClimate;
		this.planetOfOrigin = planetOfOrigin;
		this.accessLevelOrigins = accessLevelOrigins;
		this.playable = playable;
		this.inCouncilSpace = inCouncilSpace;
		this.councilRelations = councilRelations;
		this.skillModifiers = skillModifiers;
		this.accessLevelModifiers = accessLevelModifiers;
		this.accessLevel = accessLevel;
		this.image = image;
		this.accessLevelImage = accessLevelImage;
		this.source = source;
		this.accessLevelIntelligence = accessLevelIntelligence;
		this.accessLevelClimate = accessLevelClimate;
		this.playerNotes = playerNotes;
	}
	
	public Species() {
		this.accessLevel = AccessLevel.L0;
		this.accessLevelTechlevel = AccessLevel.L0;
		this.accessLevelImage = AccessLevel.L0;
		this.accessLevelOrigins = AccessLevel.L0;
		this.accessLevelIntelligence = AccessLevel.L0;
		this.accessLevelClimate = AccessLevel.L0;
		this.intelligence = Intelligence.BORDERLINE;
		this.technicalLevel = TechnicalLevel.NONE;
		this.preferredPlanetType = PlanetType.M;
		this.preferredClimate = Climate.TEMPERATE;
		this.playable = false;
		this.inCouncilSpace = false;
		this.skillModifiers = new HashMap<>();
	}

	@Override
	public String toString() { 
		return "Species: " + this.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public String getShortDescription() {
		return StringUtil.getShortDescription(this.description);
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Planet getPlanetOfOrigin() {
		return planetOfOrigin;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public AccessLevel getAccessLevelOrigins() {
		return accessLevelOrigins;
	}

	public void setAccessLevelOrigins(AccessLevel accessLevelOrigins) {
		this.accessLevelOrigins = accessLevelOrigins;
	}

	public AccessLevel getAccessLevelModifiers() {
		return accessLevelModifiers;
	}

	public void setAccessLevelModifiers(AccessLevel accessLevelModifiers) {
		this.accessLevelModifiers = accessLevelModifiers;
	}

	public void setPlanetOfOrigin(Planet planetOfOrigin) {
		this.planetOfOrigin = planetOfOrigin;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public AccessLevel getAccessLevelImage() {
		return accessLevelImage;
	}

	public void setAccessLevelImage(AccessLevel accessLevelImage) {
		this.accessLevelImage = accessLevelImage;
	}

	public AccessLevel getAccessLevelTechlevel() {
		return accessLevelTechlevel;
	}

	public void setAccessLevelTechlevel(AccessLevel accessLevelTechlevel) {
		this.accessLevelTechlevel = accessLevelTechlevel;
	}

	public TechnicalLevel getTechnicalLevel() {
		return technicalLevel;
	}

	public void setTechnicalLevel(TechnicalLevel technicalLevel) {
		this.technicalLevel = technicalLevel;
	}

	public boolean isPlayable() {
		return playable;
	}

	public void setPlayable(boolean playable) {
		this.playable = playable;
	}

	public Intelligence getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Intelligence intelligence) {
		this.intelligence = intelligence;
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

	public SpeciesCouncilRelation getCouncilRelations() {
		return councilRelations;
	}

	public void setCouncilRelations(SpeciesCouncilRelation councilRelations) {
		this.councilRelations = councilRelations;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Map<Skill, Integer> getSkillModifiers() {
		return skillModifiers;
	}

	public void setSkillModifiers(Map<Skill, Integer> skillModifiers) {
		this.skillModifiers = skillModifiers;
	}
	
	public boolean isInCouncilSpace() {
		return inCouncilSpace;
	}

	public void setInCouncilSpace(boolean inCouncilSpace) {
		this.inCouncilSpace = inCouncilSpace;
	}
	
	public AccessLevel getAccessLevelIntelligence() {
		return accessLevelIntelligence;
	}

	public void setAccessLevelIntelligence(AccessLevel accessLevelIntelligence) {
		this.accessLevelIntelligence = accessLevelIntelligence;
	}

	public AccessLevel getAccessLevelClimate() {
		return accessLevelClimate;
	}

	public void setAccessLevelClimate(AccessLevel accessLevelClimate) {
		this.accessLevelClimate = accessLevelClimate;
	}
	
	public String getPlayerNotes() {
		return playerNotes;
	}

	public void setPlayerNotes(String playerNotes) {
		this.playerNotes = playerNotes;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Species other = (Species) obj;
		return Objects.equals(id, other.id);
	}

	
	// A complicated sorting order:
	// 1. Playable first
	// 2. Is present in Council Space
	// 3. Access Level
	// 4. Technology Level
	// 5. Alphabetical
	
	@Override
	public int compareTo(Species o) {
		if (this.isPlayable() != o.isPlayable()) {
			// 1. Playable first
			return (this.isPlayable()) ? -1 : 1;
		}
		else if (this.isInCouncilSpace() != o.isInCouncilSpace()) {
			// 2. Is present in Council Space
			return (this.isInCouncilSpace()) ? -1 : 1;
		}
		else if (this.getAccessLevel() != o.getAccessLevel()) {
			// 3. Access Level
			return this.getAccessLevel().compareTo(o.getAccessLevel());
		}
		else if (this.getTechnicalLevel() != o.getTechnicalLevel()) {
			// 4. Technology Level
			return this.getTechnicalLevel().compareTo(o.getTechnicalLevel());
		}
		else {
			// 5. Alphabetical
			return this.getName().compareTo(o.getName());
		}
	}
}
