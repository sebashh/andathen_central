package nl.andathen.central.domain.galaxy.planet;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Culture;
import nl.andathen.central.domain.Species;
import nl.andathen.central.domain.resources.Resource;

@Generated(value="Dali", date="2022-08-15T16:15:39.732+0200")
@StaticMetamodel(KnownPlanet.class)
public class KnownPlanet_ extends Planet_ {
	public static volatile SingularAttribute<KnownPlanet, Double> population;
	public static volatile SingularAttribute<KnownPlanet, String> description;
	public static volatile SingularAttribute<KnownPlanet, AccessLevel> accessLevelTechlevel;
	public static volatile SingularAttribute<KnownPlanet, TechnicalLevel> technicalLevel;
	public static volatile SingularAttribute<KnownPlanet, AccessLevel> accessLevelCultures;
	public static volatile SingularAttribute<KnownPlanet, Culture> culture;
	public static volatile MapAttribute<KnownPlanet, Culture, String> cultureNames;
	public static volatile MapAttribute<KnownPlanet, String, Integer> cities;
	public static volatile SingularAttribute<KnownPlanet, AccessLevel> accessLevelSpecies;
	public static volatile MapAttribute<KnownPlanet, Species, Float> speciesPresent;
	public static volatile SetAttribute<KnownPlanet, Resource> resources;
	public static volatile SingularAttribute<KnownPlanet, AccessLevel> accessLevelResources;
	public static volatile SingularAttribute<KnownPlanet, AccessLevel> accessLevelClimates;
	public static volatile MapAttribute<KnownPlanet, Climate, Float> climates;
}
