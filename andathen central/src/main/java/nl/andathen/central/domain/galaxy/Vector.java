package nl.andathen.central.domain.galaxy;

public final class Vector {
	private double speedDistance; // in AU per year
	private double speedLongitude; // in arc seconds per year
	private double speedLatitude; // in arc seconds per year
	
	
	public Vector(double speedDistance, double speedLongitude, double speedLatitude) {
		super();
		this.speedDistance = speedDistance;
		this.speedLongitude = speedLongitude;
		this.speedLatitude = speedLatitude;
	}

	@Override
	public int hashCode() {
		return (int) (this.longHashCode() % Integer.MAX_VALUE);
	}

	public long  longHashCode() {
		final long prime = 8652132956869042727l;
		long result = 1;
		long temp;
		temp = Double.doubleToLongBits(speedDistance);
		result = prime * result + (long) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(speedLongitude);
		result = prime * result + (long) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(speedLatitude);
		result = prime * result + (long) (temp ^ (temp >>> 32));
		return result;
	}

	public double getSpeedDistance() {
		return speedDistance;
	}

	public void setSpeedDistance(double speedDistance) {
		this.speedDistance = speedDistance;
	}

	public double getSpeedLongitude() {
		return speedLongitude;
	}

	public void setSpeedLongitude(double speedLongitude) {
		this.speedLongitude = speedLongitude;
	}

	public double getSpeedLatitude() {
		return speedLatitude;
	}

	public void setSpeedLatitude(double speedLatitude) {
		this.speedLatitude = speedLatitude;
	}
}
