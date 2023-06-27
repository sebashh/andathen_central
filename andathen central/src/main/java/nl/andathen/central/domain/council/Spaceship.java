package nl.andathen.central.domain.council;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.character.Character;
import nl.andathen.central.util.image.IImageProvider;

@Entity
@Table(name="spaceship")
public class Spaceship implements Comparable <Spaceship>, IImageProvider, Serializable {
	private static final long serialVersionUID = -7956314235732759463L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "designation", unique = true, nullable = false)
	private String designation;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", columnDefinition="TEXT")
	private String description;
	@Column(name = "access_level")
	@Enumerated(EnumType.STRING)
	private AccessLevel accessLevel;
	@Column(name="price")
	private Long price;
	@Lob
	@Column(name="image", columnDefinition="MEDIUMBLOB", nullable=true)
	private BufferedImage image;
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name="spaceship_class", nullable=false)
	private SpaceshipClass spaceshipClass;
	@OneToOne
	@JoinColumn(name="id")
	private Character character;
	
	public Spaceship() {
		
	}

	public Spaceship(String designation, String name, String description, SpaceshipClass spaceshipClass, AccessLevel accessLevel) {
		this(designation, name, description, null, spaceshipClass, accessLevel, null);
	}
	
	public Spaceship(String designation, String name, String description, Long price, SpaceshipClass spaceshipClass, AccessLevel accessLevel) {
		this(designation, name, description, price, spaceshipClass, accessLevel, null);
	}

	public Spaceship(String designation, String name, String description, Long price, SpaceshipClass spaceshipClass, AccessLevel accessLevel, BufferedImage image) {
		super();
		this.designation = designation;
		this.name = name;
		this.description = description;
		this.accessLevel = accessLevel;
		this.image = image;
		this.price = price;
		this.spaceshipClass = spaceshipClass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		if (description == null) {
			if (spaceshipClass == null) {
				return null;
			}
			else {
				return spaceshipClass.getDescription();
			}
		}
		else {
			return this.description;
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccessLevel getAccessLevel() {
		if (accessLevel == null) {
			if (spaceshipClass == null) {
				return null;
			}
			else {
				return spaceshipClass.getAccessLevel();
			}
		}
		else {
			return this.accessLevel;
		}
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public BufferedImage getImage() {
		if (image == null) {
			return spaceshipClass.getImage();
		}
		else {
			return this.image;
		}
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Long getPrice() {
		if (price == null) {
			if (spaceshipClass == null) {
				return null;
			}
			else {
				return spaceshipClass.getBasePrice();
			}
		}
		else {
			return price;
		}
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public SpaceshipClass getSpaceshipClass() {
		return spaceshipClass;
	}

	public void setSpaceshipClass(SpaceshipClass spaceshipClass) {
		this.spaceshipClass = spaceshipClass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(designation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spaceship other = (Spaceship) obj;
		return Objects.equals(designation, other.designation);
	}

	@Override
	public String toString() {
		return "Spaceship [id=" + id + ", designation=" + designation + ", name=" + name + ", description="
				+ description + ", accessLevel=" + accessLevel + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Spaceship o) {
		return this.designation.compareTo(o.designation);
	}
}
