package nl.andathen.central.domain.galaxy;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.galaxy.planet.Planet;
import nl.andathen.central.domain.galaxy.planet.PlanetType.Habitability;

@Entity  
@Table(name="star", indexes = { @Index(name = "IDX_STAR_TYPE", columnList = "type"),
								@Index(name = "IDX_STAR_SUBTYPE", columnList = "type, temperature_sequence"),
								@Index(name = "IDX_DISTANCE_CIADAN", columnList = "distance_to_ciadan")})

public class Star extends Body implements Comparable<Star>, Cloneable {
	@Column(nullable=false)
	@Enumerated(EnumType.STRING) 
	private StarType type;
	
	@Column(name="temperature_sequence", nullable=false)
	private int temperatureSequence;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private LuminosityClass luminosity;
	
	@Column(name="absolute_magnitude", nullable=false)
	private float absoluteMagnitude;
	
	@Embedded
	private Coordinate coordinate;
	
	@Column(name="goldilock_zone_start", nullable=true)
	private double goldilockZoneStart; //In AU
	
	@Column(name="goldilock_zone_end", nullable=true)
	private double goldilockZoneEnd; //In AU
	
	@Column(name="distance_to_ciadan", nullable=false)
	private double distanceToCiadan;
	
	@OneToOne(targetEntity=Star.class,cascade=CascadeType.ALL, optional=true)
	private Star sister = null;
	
	@OneToMany(fetch = FetchType.EAGER)  
	@JoinColumn(name="star")
	@Fetch(FetchMode.SELECT)
	private SortedSet<Planet> planets;

	public Star() {
		
	}

	public Star(	String designation, String name, String description, StarType type, int temperatureSequence, LuminosityClass luminosity,
					float absoluteMagnitude, double distance, double longitude, double latitude, double goldilockZoneStart,
					double goldilockZoneEnd, double distanceToCiadan, Star sister, AccessLevel accessLevel, BodyImage image) {
		this(designation, name, description, type, temperatureSequence, luminosity,
				absoluteMagnitude, new Coordinate(distance, longitude, latitude), goldilockZoneStart,
				goldilockZoneEnd, distanceToCiadan, sister, accessLevel, image);
	}
	
	public Star(String designation, String name, String description, StarType type, int temperatureSequence, LuminosityClass luminosity,
			float absoluteMagnitude, Coordinate coordinate, double goldilockZoneStart,
			double goldilockZoneEnd, double distanceToCiadan, Star sister, AccessLevel accessLevel, BodyImage image) {
		super(designation, name, description, accessLevel, image);
		this.type = type;
		this.temperatureSequence = temperatureSequence;
		this.luminosity = luminosity;
		this.absoluteMagnitude = absoluteMagnitude;
		this.coordinate = coordinate;
		this.goldilockZoneStart = goldilockZoneStart;
		this.goldilockZoneEnd = goldilockZoneEnd;
		this.distanceToCiadan = distanceToCiadan;
		this.sister = sister;
		this.planets = new TreeSet<>(new PlanetDistanceComparator());
	}


	public boolean addPlanet(Planet p) {
		if (planets == null) {
			this.planets = new TreeSet<>(new PlanetDistanceComparator());
		}
		return planets.add(p);
	}
	
	public Planet getPlanet(int index) {
		for (Planet p: planets) {
			if (p.getPlanetIndex() == index) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public Star clone() {
		return new Star(this.getDesignation(), this.getName(), this.getDescription(), this.getType(), this.getTemperatureSequence(), this.getLuminosity(), 
						this.getAbsoluteMagnitude(), this.getCoordinate(), this.getGoldilockZoneStart(), 
						this.getGoldilockZoneEnd(), this.getDistanceToCiadan(), this.getSister(), getAccessLevel(), this.getBodyImage());
	}
	
	public SortedSet<Planet> getPlanets() {
		SortedSet<Planet> result = new TreeSet<>();
		result.addAll(planets);
		return result;
	}

	public StarType getType() {
		return type;
	}

	public int getTemperatureSequence() {
		return temperatureSequence;
	}

	public LuminosityClass getLuminosity() {
		return luminosity;
	}

	public float getAbsoluteMagnitude() {
		return absoluteMagnitude;
	}

	public double getGoldilockZoneStart() {
		return goldilockZoneStart;
	}

	public double getGoldilockZoneEnd() {
		return goldilockZoneEnd;
	}
	
	public  Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Star getSister() {
		return sister;
	}

	public void setSister(Star sister) {
		this.sister = sister;
	}

	public double getDistanceToCiadan() {
		return distanceToCiadan;
	}

	public void setDistanceToCiadan(double distanceToCiadan) {
		this.distanceToCiadan = distanceToCiadan;
	}

	@Override
	public int hashCode() {
		return (int) (this.longHashCode() % Integer.MAX_VALUE);
	}
	
	public long longHashCode() {
		final long prime = 4675813100949394889l;
		long result = super.hashCode();
		result = prime * result + ((coordinate == null) ? 0 : coordinate.longHashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Star other = (Star) obj;
		return Objects.equals(coordinate, other.coordinate);
	}

	public void setType(StarType type) {
		this.type = type;
	}

	public void setTemperatureSequence(int temperatureSequence) {
		this.temperatureSequence = temperatureSequence;
	}

	public void setLuminosity(LuminosityClass luminosity) {
		this.luminosity = luminosity;
	}

	public void setAbsoluteMagnitude(float absoluteMagnitude) {
		this.absoluteMagnitude = absoluteMagnitude;
	}

	public void setGoldilockZoneStart(double goldilockZoneStart) {
		this.goldilockZoneStart = goldilockZoneStart;
	}

	public void setGoldilockZoneEnd(double goldilockZoneEnd) {
		this.goldilockZoneEnd = goldilockZoneEnd;
	}

	public void setPlanets(SortedSet<Planet> planets) {
		this.planets = new TreeSet<>(new PlanetDistanceComparator());
		this.planets.addAll(planets);
	}
	
	public double getDistanceToCiadanRounded() {
		return Math.floor(distanceToCiadan*100) / 100d;
	}

	@Override
	public int compareTo(Star o) {
		return this.getDesignation().compareTo(o.getDesignation());
	}

	public boolean isDoubleStar() {
		return (sister != null);
	}
	
	public int getNumberOfPlanets() {
		if (planets != null) {
			return planets.size();
		}
		else {
			return 0;
		}
	}
	
	public int getNumberOfInhabitablePlanets() {
		if (planets == null) {
			return 0;
		}
		int result = 0;
		for (Planet p: planets) {
			if ((p.getType().getHabitability() == Habitability.YES) || (p.getType().getHabitability() == Habitability.MARGINALLY)) {
				result++;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Star [type=" + type + ", temperatureSequence=" + temperatureSequence + ", luminosity=" + luminosity
				+ ", absoluteMagnitude=" + absoluteMagnitude + ", coordinate=" + coordinate + ", goldilockZoneStart="
				+ goldilockZoneStart + ", goldilockZoneEnd=" + goldilockZoneEnd + ", distanceToCiadan="
				+ distanceToCiadan + ", sister=" + sister + "]";
	}
}
