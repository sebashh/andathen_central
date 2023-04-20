package nl.andathen.central.domain;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nl.andathen.central.util.IImageProvider;
import nl.andathen.central.util.StringUtil;

// The information player characters unearth often comes from a source other than their own observations.
// Those sources are modelled here. An example of such a source is a probe from the Exploratory Forces,
// or the private database of a company.
//

@Entity
@Table(name="source", indexes = { 	@Index(name = "IDX_SOURCE_REGNUMBER", columnList = "registration_number"),
									@Index(name = "IDX_SOURCE_NAME", columnList = "name")})

public class Source implements Comparable<Source> , IImageProvider, Serializable{
	private static final long serialVersionUID = -576129883349984733L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="registration_number", unique=true, nullable=false)
	private Long registrationNumber;
	@Column(name="name", unique=false, nullable=false)
	private String name; 
	@Column(name="description", nullable=false)
	private String description; 
	@Column(name="access_level", nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevel;
	@Lob
	@Column(name="image", columnDefinition="MEDIUMBLOB")
	private BufferedImage image;
	@ManyToOne
	@JoinColumn(name = "owner")
	private Organization owner;

	public Source(String name, String description, AccessLevel accessLevel, Organization owner, Long registrationNumber, BufferedImage image) {
		super();
		this.name = name;
		this.description = description;
		this.accessLevel = accessLevel;
		this.owner = owner;
		this.registrationNumber = longHashCode();
		this.image = image;
	}
	
	
	public Source() {
		super();
	}


	@Override
	public int hashCode() {
		return (int) (this.longHashCode() % Integer.MAX_VALUE);
	}
	
	public long  longHashCode() {
		final long prime = 60410923346665403l;
		long result = 1;
		long temp;
		temp = accessLevel.getDescription().hashCode();
		result = prime * result + (long) (temp ^ (temp >>> 32));
		temp = name.hashCode();
		result = prime * result + (long) (temp ^ (temp >>> 32));
		temp = description.hashCode();
		result = prime * result + (long) (temp ^ (temp >>> 32));
		temp = owner.longHashCode();
		result = prime * result + (long) (temp ^ (temp >>> 32));
		return result;
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

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Organization getOwner() {
		return owner;
	}

	public void setOwner(Organization owner) {
		this.owner = owner;
	}
	

	public Long getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(Long registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}


	@Override
	public int compareTo(Source o) {
		if (this.getAccessLevel().equals(o.getAccessLevel())) {
			return this.getName().compareTo(o.getName());
		}
		else {
			return this.getAccessLevel().compareTo(o.getAccessLevel());
		}
	}
	
	public String getShortDescription() {
		return StringUtil.getShortDescription(this.description);
	}
	
	@Override
	public String toString() { 
		return "Source: " + this.getName();
	}
	
	public String getGUIString() {
		return this.getName() + " (" + this.getRegistrationNumber() + ")";
	}
}
