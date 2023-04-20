package nl.andathen.central.domain.person;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="country") 
public class Country implements Serializable, Comparable<Country> {
	private static final long serialVersionUID = 6570873207114017089L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="name", unique=true, nullable=false)
	private String name;
	@Column(name="alternate_name",unique=true,nullable=true)
	private String alternateName;
	@Column(name="description", nullable=true, columnDefinition="TEXT")
	private String description;
	
	public Country() {
		
	}

	public Country(String name, String alternateName, String description) {
		super();
		this.name = name;
		this.alternateName = alternateName;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAlternateName() {
		return alternateName;
	}

	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Country o) {
		return this.name.compareTo(o.getName());
	}

}
