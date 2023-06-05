package nl.andathen.central.domain.council;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.andathen.central.domain.AccessLevel;

@Generated(value="Dali", date="2023-06-05T14:26:37.459+0200")
@StaticMetamodel(Spaceship.class)
public class Spaceship_ {
	public static volatile SingularAttribute<Spaceship, Long> id;
	public static volatile SingularAttribute<Spaceship, String> designation;
	public static volatile SingularAttribute<Spaceship, String> name;
	public static volatile SingularAttribute<Spaceship, String> description;
	public static volatile SingularAttribute<Spaceship, AccessLevel> accessLevel;
	public static volatile SingularAttribute<Spaceship, Long> price;
	public static volatile SingularAttribute<Spaceship, SpaceshipClass> spaceshipClass;
}
