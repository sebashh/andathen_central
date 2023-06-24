package nl.andathen.central.domain.galaxy;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import nl.andathen.central.util.image.IImageProvider;

// Images will be stored in the database, but that will take up a lot of space. 
// To prevent the table file from growing too large images for bodies are to be 
// stored in this separate table
//
// Not all tables need this; for some another class will be created
// Not ideal from the design perspective, but necessary for technical reasons
//

@Entity 
@Table(name="body_image")
public class BodyImage implements Serializable, IImageProvider {
	private static final long serialVersionUID = 4804427860915878990L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Lob
	@Column(name="image", columnDefinition="MEDIUMBLOB", nullable=false)
	private BufferedImage image;

	public BodyImage() {

	}

	public BodyImage(Long id, BufferedImage image) {
		super();
		this.id = id;
		this.image = image;
	}
	
	public BodyImage(BufferedImage image) {
		super();
		this.image = image;
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
		BodyImage other = (BodyImage) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "BodyImage [id=" + id + ", image=" + image + "]";
	}
}
