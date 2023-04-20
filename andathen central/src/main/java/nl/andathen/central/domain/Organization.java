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
import javax.persistence.Lob;
import javax.persistence.Table;

import nl.andathen.central.util.IImageProvider;
import nl.andathen.central.util.StringUtil;

// An organization with effect on the game. Examples are the Exploration Forces, Defense Forces, Trade Forces, Senate, Trade Houses etc.
// This class probably will have a bunch of subclasses, such as TradeHouse or Military
//

@Entity
@Table(name="organization", indexes = { 	@Index(name = "IDX_ORGANIZATION_REGNUMBER", columnList = "registration_number"),
											@Index(name = "IDX_ORGANIZATION_NAME", columnList = "name")})

public class Organization implements Comparable<Organization>, IImageProvider, Serializable {
	private static final long serialVersionUID = -3416738963460344853L;
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

	public Organization(Long registrationNumber, String name, String description, AccessLevel accessLevel, BufferedImage image) {
		super();
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.description = description;
		this.accessLevel = accessLevel;
		this.registrationNumber = longHashCode();
		this.image = image;
	}
	
	public Organization(Long registrationNumber, String name, AccessLevel accessLevel, BufferedImage image) {
		this(registrationNumber, name, null, accessLevel, image);
	}

	public Organization() {
		this.accessLevel = AccessLevel.L0;
	}

	@Override
	public int hashCode() {
		return (int) (this.longHashCode() % Integer.MAX_VALUE);
	}
	
	public long  longHashCode() {
		final long prime = 86796792733316639l;
		long result = 1;
		long temp;
		temp = accessLevel.getDescription().hashCode();
		result = prime * result + (long) (temp ^ (temp >>> 32));
		temp = name.hashCode();
		result = prime * result + (long) (temp ^ (temp >>> 32));
		temp = description.hashCode();
		result = prime * result + (long) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organization other = (Organization) obj;
		if (accessLevel != other.accessLevel)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
	
	public String getShortDescription() {
		return StringUtil.getShortDescription(this.description);
	}
	
	@Override
	public int compareTo(Organization o) {
		if (this.getAccessLevel().equals(o.getAccessLevel())) {
			return this.getName().compareTo(o.getName());
		}
		else {
			return this.getAccessLevel().compareTo(o.getAccessLevel());
		}
	}
	
	@Override
	public String toString() { 
		return "Organization: " + this.getName();
	}
}
