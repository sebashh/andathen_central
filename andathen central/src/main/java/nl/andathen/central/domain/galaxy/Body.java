package nl.andathen.central.domain.galaxy;

import java.awt.image.BufferedImage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import nl.andathen.central.domain.AccessLevel;

@Entity 
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="Body", indexes = { @Index(name = "IDX_BODY_NAME", columnList = "name"),
								@Index(name = "IDX_ACCESS_LEVEL", columnList = "access_level")})

public abstract class Body {
	
	@Id
	@Column (name="designation", nullable=false)
	private String designation; // The designation in Council records
	
	@Column(name="name",nullable=true)  
	private String name; // The name as known in Council Space records
	
	@Column(name="access_level",nullable=false)
	@Enumerated(EnumType.STRING) 
	private AccessLevel accessLevel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "image_id")
    private BodyImage bodyImage;
	
	@Column(name="description", columnDefinition="TEXT", nullable=true)
	private String description;

	public Body() {
		
	}

	public Body(String designation, String name, String description, AccessLevel accessLevel, BodyImage bodyImage) {
		super();
		this.designation = designation;
		this.name = name;
		this.accessLevel = accessLevel;
		this.bodyImage = bodyImage;
		this.description = description;
	}
	
	public Body(String designation, String name, String description, AccessLevel accessLevel, BufferedImage image) {
		super();
		this.designation = designation;
		this.name = name;
		this.accessLevel = accessLevel;
		this.bodyImage = new BodyImage(image);
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public BodyImage getBodyImage() {
		return bodyImage;
	}

	public void setBodyImage(BodyImage bodyImage) {
		this.bodyImage = bodyImage;
	}
	

	public String getCommonName() {
		if (name != null) {
			return name + " (" + designation + ")";
		}
		else {
			return designation;
		}
	}
	
	public void setImage(BufferedImage image) {
		if (this.getBodyImage() != null) {
			this.bodyImage.setImage(image);
		}
	}
	
	public BufferedImage getImage() {
		if (this.getBodyImage() == null) {
			return null;
		}
		else {
			return this.bodyImage.getImage();
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public double calculateDistance(Body other) {
		return this.getCoordinate().calculateDistance(other.getCoordinate());
	}
	
	public abstract Coordinate getCoordinate();

}
