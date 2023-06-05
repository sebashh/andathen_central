package nl.andathen.central.domain.council;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.util.image.IImageProvider;

@Entity
@Table(name="spaceship_class")
public class SpaceshipClass implements Comparable <SpaceshipClass>, IImageProvider, Serializable {
	private static final long serialVersionUID = 4047647153280983967L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	@Column(name = "description", nullable = true, columnDefinition="TEXT")
	private String description;
	@Column(name = "access_level", nullable = false)
	@Enumerated(EnumType.STRING)
	private AccessLevel accessLevel;
	@Column(name = "ship_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ShipType shipType;
	@Column(name="base_price", nullable = true)
	private Long basePrice;
	@Lob
	@Column(name="default_image", columnDefinition="MEDIUMBLOB", nullable=true)
	private BufferedImage image;
	
	public SpaceshipClass() {
		
	}

	public SpaceshipClass(String name, String description, ShipType shipType, AccessLevel accessLevel) {
		this(name, description, shipType, null, accessLevel, null);
	}
	
	public SpaceshipClass(String name, String description, ShipType shipType, Long basePrice, AccessLevel accessLevel) {
		this(name, description, shipType, basePrice, accessLevel, null);
	}

	public SpaceshipClass(String name, String description, ShipType shipType, Long basePrice, AccessLevel accessLevel, BufferedImage image) {
		super();
		this.name = name;
		this.description = description;
		this.accessLevel = accessLevel;
		this.image = image;
		this.basePrice = basePrice;
		this.shipType = shipType;
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

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Long getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Long basePrice) {
		this.basePrice = basePrice;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
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
		SpaceshipClass other = (SpaceshipClass) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() { 
		return "Class: " + this.getName();
	}

	@Override
	public int compareTo(SpaceshipClass o) {
		if (this.shipType.equals(o.shipType)) { 
			return this.name.compareTo(o.name);
		}
		else {
			return this.shipType.compareTo(o.shipType);
		}
	}
}
