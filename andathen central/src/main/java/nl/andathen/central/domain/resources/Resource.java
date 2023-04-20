package nl.andathen.central.domain.resources;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.util.IImageProvider;
import nl.andathen.central.util.StringUtil;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="Resource", indexes = { @Index(name = "IDX_RESOURCE_NAME", columnList = "name"),
									@Index(name = "IDX_RESOURCE_SCARCITY", columnList = "scarcity"),
									@Index(name = "IDX_RESOURCE_ACCESS_LEVEL", columnList = "access_level")})

public class Resource implements Comparable <Resource>, IImageProvider, Serializable {
	private static final long serialVersionUID = -1482985694882083114L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="name" , nullable=false, unique=true)
	private String name;
	@Column(name="description" , nullable=false, columnDefinition="TEXT")
	private String description;
	@Column(name="player_notes" , nullable=true, columnDefinition="TEXT")
	private String playerNotes;
	@Column(name="scarcity", nullable = false)
	@Enumerated(EnumType.STRING)
	private Scarcity scarcity;
	@Digits(integer=9, fraction=2)
	@Column(nullable= false, precision=11, scale=2, name="base_price")
	@Min(value = (long) 0.1)
	private BigDecimal basePrice; // In Ciadan, per unit. Divisible in cents.
	@Column(name="access_level", nullable = false)
	@Enumerated(EnumType.STRING)
	private AccessLevel accessLevel;
	@Lob
	@Column(name="image", columnDefinition="MEDIUMBLOB")
	private BufferedImage image;
	
	public Resource(Long id, String name, String description, String playerNotes, Scarcity scarcity, BigDecimal basePrice, 
			AccessLevel accessLevel, BufferedImage image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.scarcity = scarcity;
		this.basePrice = basePrice;
		this.accessLevel = accessLevel;
		this.image = image;
		this.playerNotes = playerNotes;
	}

	public Resource() {
		this.scarcity = Scarcity.COMMON;
		this.accessLevel = AccessLevel.L0;
	}
	
	public String getShortDescription() {
		return StringUtil.getShortDescription(this.description);
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

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Scarcity getScarcity() {
		return scarcity;
	}

	public void setScarcity(Scarcity scarcity) {
		this.scarcity = scarcity;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	@Override
	public BufferedImage getImage() {
		return this.image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
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
		Resource other = (Resource) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Resource [name=" + name + ", description=" + description + ", scarcity=" + scarcity + ", basePrice="
				+ basePrice + ", accessLevel=" + accessLevel + ", image=" + image.getClass() + "]";
	}

	@Override
	public int compareTo(Resource o) {
		if (this.getScarcity().equals(o.getScarcity())) {
			return this.getName().compareTo(o.getName());
		}
		else {
			return this.getScarcity().compareTo(o.getScarcity());
		}
	}
}
