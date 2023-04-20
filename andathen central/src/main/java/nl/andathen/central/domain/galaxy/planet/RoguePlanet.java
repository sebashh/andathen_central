package nl.andathen.central.domain.galaxy.planet;

import nl.andathen.central.domain.galaxy.Body;
import nl.andathen.central.domain.galaxy.Coordinate;
import nl.andathen.central.domain.galaxy.Vector;

public class RoguePlanet extends Body {
	private PlanetType type;
	private Coordinate coordinate;
	private Vector vector;
	private double distanceToCiadan;

	@Override
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public PlanetType getType() {
		return type;
	}

	public void setType(PlanetType type) {
		this.type = type;
	}

	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}

	public double getDistanceToCiadan() {
		return distanceToCiadan;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
}
