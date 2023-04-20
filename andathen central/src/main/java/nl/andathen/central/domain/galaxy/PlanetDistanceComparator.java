package nl.andathen.central.domain.galaxy;

import java.util.Comparator;

import nl.andathen.central.domain.galaxy.planet.Planet;

public class PlanetDistanceComparator implements Comparator<Planet> {

	@Override
	public int compare(Planet o1, Planet o2) {
		if (o1.getAverageDistanceToStar() < o2.getAverageDistanceToStar()) {
			return -1;
		}
		else if (o1.getAverageDistanceToStar() < o2.getAverageDistanceToStar()) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
