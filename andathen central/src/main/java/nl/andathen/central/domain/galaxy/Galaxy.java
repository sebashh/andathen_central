package nl.andathen.central.domain.galaxy;

public class Galaxy extends Body {
	private Coordinate coordinate;
	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public Coordinate getCoordinate() {
		return coordinate;
	}
}
