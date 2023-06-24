package nl.andathen.central.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import nl.andathen.central.util.StringUtil;

/**
 * Class containing information about a culture.
 * Class also used by Hibernate.
 * @author Annotation by Dani Bakker
 */
@Indexed
@Entity
@Table(name = "culture", indexes = {
	@Index(name = "IDX_CULTURE_NAME", columnList ="name"),
	@Index(name = "IDX_CULTURE_ACCESS_LEVEL", columnList ="access_level"),
	@Index(name = "IDX_CULTURE_SPECIES_ID", columnList ="species_id")
})
public class Culture implements Comparable<Culture> {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	@Column(name = "description", nullable = true, columnDefinition="TEXT")
	private String description;
	@ManyToOne
	@JoinColumn(name = "species_id")
	private Species species;
	@Column(name = "access_level", nullable = false)
	@Enumerated(EnumType.STRING)
	private AccessLevel accessLevel;

	public Culture(String name, String description, Species species, AccessLevel accessLevel) {
		super();
		this.name = name;
		this.description = description;
		this.species = species;
		this.accessLevel = accessLevel;
	}

	public Culture() {
		this.accessLevel = AccessLevel.L0;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	public String getShortDescription() {
		return StringUtil.getShortDescription(this.description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Species getSpecies() {
		return species;
	}
	
	public void setSpecies(Species specie) {
		this.species = specie;
	}
	
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Override
	public int compareTo(Culture o) {
		if (this.getId() != o.getId()) {
			return this.getName().compareTo(o.getName());
		}
		else {
			return this.getId().compareTo(o.getId());
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Culture other = (Culture) obj;
		return Objects.equals(getId(), other.getId());
	}
}
