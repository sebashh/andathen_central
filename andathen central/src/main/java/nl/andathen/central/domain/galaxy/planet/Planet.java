package nl.andathen.central.domain.galaxy.planet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nl.andathen.central.domain.galaxy.Body;
import nl.andathen.central.domain.galaxy.Coordinate;
import nl.andathen.central.domain.galaxy.Star;

@Entity 
@Table(name="planet", indexes = { @Index(name = "IDX_PLANET_STAR", columnList = "star"),
								  @Index(name = "IDX_PLANET_TYPE", columnList = "type"),
								  @Index(name = "IDX_STAR_PLANET_INDEX", columnList = "designation, planet_index"),
								  @Index(name = "IDX_PLANET_LOCATION", columnList = "star, planet_index")})

public class Planet extends Body implements Comparable<Planet> {
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name="star", nullable=false)
	private Star star;
	
	@Column(name="planet_index")
	private int planetIndex;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING) 
	private PlanetType type;
	
	@Column(name="average_distance_to_star", nullable=false)
	private float averageDistanceToStar; // in AU
	
	@Column(nullable=false)
	private float tilt = 0; // degrees relative to the plane of the star system
	
	@Column(nullable=false)
	private float excentricity; // in degrees
	
	public Planet() {
		
	}
	
	@Override
	public int hashCode() {
		return (int) (this.longHashCode() % Integer.MAX_VALUE);
	}
	
	public long longHashCode() {
		long prime = 2031055211850435299l;
		long result = super.hashCode();
		result = prime * result + (long) Float.floatToIntBits(averageDistanceToStar);
		result = prime * result + (long) Float.floatToIntBits(excentricity);
		result = prime * result + ((star == null) ? 0 : star.longHashCode());
		result = prime * result + (long) Float.floatToIntBits(tilt);
		result = prime * result + ((type == null) ? 0 : (long) type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (this.getDesignation().equals(other.getDesignation())) {
			return true;
		}
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (planetIndex != other.planetIndex)
			return false;
		if (star == null) {
			if (other.star != null)
				return false;
		} else if (!star.equals(other.star))
			return false;
		return true;
	}

	public PlanetType getType() {
		return type;
	}

	public void setType(PlanetType type) {
		this.type = type;
	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}

	public float getAverageDistanceToStar() {
		return averageDistanceToStar;
	}

	public float getTilt() {
		return tilt;
	}

	public float getExcentricity() {
		return excentricity;
	}
	
	@Override
	public Coordinate getCoordinate() {
		return star.getCoordinate();
	}
	
	public int getPlanetIndex() {
		return planetIndex;
	}

	public void setPlanetIndex(int planetIndex) {
		this.planetIndex = planetIndex;
	}

	public void setAverageDistanceToStar(float averageDistanceToStar) {
		this.averageDistanceToStar = averageDistanceToStar;
	}

	public void setTilt(float tilt) {
		this.tilt = tilt;
	}

	public void setExcentricity(float excentricity) {
		this.excentricity = excentricity;
	}

	@Override
	public int compareTo(Planet o) {
		if (!this.star.equals(o.star)) {
			return this.star.compareTo(o.star);
		}
		else {
			return this.planetIndex - o.planetIndex;
		}
	}
}
