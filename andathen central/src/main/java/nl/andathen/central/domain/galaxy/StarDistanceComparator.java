package nl.andathen.central.domain.galaxy;

import java.util.Comparator;

public class StarDistanceComparator implements Comparator<Star> {

	@Override
	public int compare(Star o1, Star o2) {
		if (o1.getDistanceToCiadan() > o2.getDistanceToCiadan()) {
			return 1;
		}
		else if (o1.getDistanceToCiadan() < o2.getDistanceToCiadan()) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
