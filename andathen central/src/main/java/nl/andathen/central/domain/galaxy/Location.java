package nl.andathen.central.domain.galaxy;

import java.util.Objects;

// A location on some Body, usually a planet but not necessarily
// The GPS is given as Earth longitude and latitude
//
public class Location {
	private Body body;
	private double longitude;
	private double latitude;
	
	public Location(Body body, double longitude, double latitude) {
		super();
		this.body = body;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Location() {

	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(body, other.body)
				&& Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
	}
}
