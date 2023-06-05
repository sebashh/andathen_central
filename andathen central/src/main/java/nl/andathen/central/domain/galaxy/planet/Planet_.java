package nl.andathen.central.domain.galaxy.planet;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.andathen.central.domain.galaxy.Body_;
import nl.andathen.central.domain.galaxy.Star;

@Generated(value="Dali", date="2023-06-05T09:40:37.655+0200")
@StaticMetamodel(Planet.class)
public class Planet_ extends Body_ {
	public static volatile SingularAttribute<Planet, Star> star;
	public static volatile SingularAttribute<Planet, Integer> planetIndex;
	public static volatile SingularAttribute<Planet, PlanetType> type;
	public static volatile SingularAttribute<Planet, Float> averageDistanceToStar;
	public static volatile SingularAttribute<Planet, Float> tilt;
	public static volatile SingularAttribute<Planet, Float> excentricity;
}
