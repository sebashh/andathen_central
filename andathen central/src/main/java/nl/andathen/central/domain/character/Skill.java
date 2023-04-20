package nl.andathen.central.domain.character;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import nl.andathen.central.util.StringUtil;

@Indexed
@Entity
@Table(name="skill", indexes = { @Index(name = "IDX_SKILL_NAME", columnList = "name")})
public class Skill implements Comparable<Skill>, Serializable {
	private static final long serialVersionUID = -8528464582090343594L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="name", unique=true)
	private String name;
	@Column(name="description" , nullable = false, columnDefinition="TEXT")
	@Field
	private String description;
	@Column(name="player_notes" , nullable=true, columnDefinition="TEXT")
	@Field
	private String playerNotes;
	@Column(name="category")
	@Enumerated(EnumType.STRING)
	private SkillCategory category;
	@Column(name="age_experience_available")
	private boolean ageExperience;
	@OneToOne(targetEntity=Skill.class,cascade=CascadeType.MERGE, optional=true)
	private Skill prerequisite;
	
	public Skill(Long id, String name, String description, String playerNotes, SkillCategory category, boolean ageExperience, Skill prerequisite) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.ageExperience = ageExperience;
		this.prerequisite = prerequisite;
		this.playerNotes = playerNotes;
	}
	
	public Skill() {

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

	public boolean isAgeExperience() {
		return ageExperience;
	}

	public void setAgeExperience(boolean ageExperience) {
		this.ageExperience = ageExperience;
	}
	
	public SkillCategory getCategory() {
		return category;
	}

	public void setCategory(SkillCategory category) {
		this.category = category;
	}

	public String getPlayerNotes() {
		return playerNotes;
	}

	public void setPlayerNotes(String playerNotes) {
		this.playerNotes = playerNotes;
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
		Skill other = (Skill) obj;
		return Objects.equals(id, other.id);
	}

	// Complicated sort order
	// 1. On category
	// 2. If a is a prerequisite for b then a comes first
	// 3. Alphabetically
	//
	@Override
	public int compareTo(Skill other) {
		if (this.getCategory() != other.getCategory()) {
			return this.getCategory().getIndex() - other.getCategory().getIndex();
		}
		else if (isPrerequisite(this, other)) {
			return -1;
		}
		else {
			return this.getName().compareTo(other.getName());
		}
	}
	
	// Check whether the potential is a prerequisite of target
	private static boolean isPrerequisite(Skill target, Skill potential) {
		if (potential.equals(target)) {
			return true;
		}
		else if (potential.getPrerequisite() == null) {
			return false;
		}
		else if (potential.getPrerequisite().equals(target)) {
			return true;
		}
		else {
			return isPrerequisite(potential, potential.getPrerequisite());
		}
	}

	public Skill getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(Skill prerequisite) {
		this.prerequisite = prerequisite;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	public String getGUIString() {
		return this.getName() + " (" + this.getCategory().getCategory() + ")";
	}
}
