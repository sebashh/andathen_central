package nl.andathen.central.domain.galaxy;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.andathen.central.domain.galaxy.planet.Planet;

@Generated(value="Dali", date="2023-06-05T09:40:37.687+0200")
@StaticMetamodel(Star.class)
public class Star_ extends Body_ {
	public static volatile SingularAttribute<Star, StarType> type;
	public static volatile SingularAttribute<Star, Integer> temperatureSequence;
	public static volatile SingularAttribute<Star, LuminosityClass> luminosity;
	public static volatile SingularAttribute<Star, Float> absoluteMagnitude;
	public static volatile SingularAttribute<Star, Coordinate> coordinate;
	public static volatile SingularAttribute<Star, Double> goldilockZoneStart;
	public static volatile SingularAttribute<Star, Double> goldilockZoneEnd;
	public static volatile SingularAttribute<Star, Double> distanceToCiadan;
	public static volatile SingularAttribute<Star, Star> sister;
	public static volatile SetAttribute<Star, Planet> planets;
}
