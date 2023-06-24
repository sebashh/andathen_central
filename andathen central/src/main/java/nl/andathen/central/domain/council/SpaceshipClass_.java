package nl.andathen.central.domain.council;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import nl.andathen.central.domain.AccessLevel;

@Generated(value="Dali", date="2023-06-05T14:26:37.461+0200")
@StaticMetamodel(SpaceshipClass.class)
public class SpaceshipClass_ {
	public static volatile SingularAttribute<SpaceshipClass, Long> id;
	public static volatile SingularAttribute<SpaceshipClass, String> name;
	public static volatile SingularAttribute<SpaceshipClass, String> description;
	public static volatile SingularAttribute<SpaceshipClass, AccessLevel> accessLevel;
	public static volatile SingularAttribute<SpaceshipClass, ShipType> shipType;
	public static volatile SingularAttribute<SpaceshipClass, Long> basePrice;
}
