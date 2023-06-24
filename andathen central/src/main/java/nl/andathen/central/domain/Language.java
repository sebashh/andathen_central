package nl.andathen.central.domain;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import nl.andathen.central.util.StringUtil;
import nl.andathen.central.util.image.IImageProvider;

@Indexed
@Entity
@Table(name="language", indexes = { @Index(name = "IDX_LANGUAGE_ISOCODE", columnList = "iso_code"),
									@Index(name = "IDX_LANGUAGE_NAME", columnList = "name"),
									@Index(name = "IDX_LANGUAGE_TYPE", columnList = "type")})

public class Language implements Comparable<Language>, IImageProvider, Serializable {
	private static final long serialVersionUID = -428994991652208047L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="iso_code", unique=true)
	private String iso;
	@Column(name="name")
	@FullTextField
	private String name;
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private LanguageType type;
	@Column(name="description" , columnDefinition="TEXT")
	@FullTextField
	private String description;
	@Lob
	@Column(name="image", columnDefinition="MEDIUMBLOB")
	private BufferedImage image;

	public Language(Long id, String iso, String name, LanguageType type, String description, BufferedImage image) {
		super();
		this.id = id;
		this.iso = iso.toLowerCase();
		this.name = name;
		this.type = type;
		this.description = description;
		this.image = image;
	}
	
	public Language() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LanguageType getType() {
		return type;
	}

	public void setType(LanguageType type) {
		this.type = type;
	}

	public String getIso() {
		return iso;
	}

	public String getDescription() {
		if (description == null) {
			return "";
		}
		else {
			return description;
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}
	
	public String getShortDescription() {
		return StringUtil.getShortDescription(this.description);
	}
	
	@Override
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(Language o) {
		if (!this.getType().name().equals(o.getType().name())) {
			return o.getType().compareTo(this.getType());
		}
		else {
			return this.getName().compareTo(o.getName());
		}
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
		Language other = (Language) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Language [iso=" + iso + ", name=" + name + ", type=" + type + ", description=" + description + "]";
	}
	
	
}
