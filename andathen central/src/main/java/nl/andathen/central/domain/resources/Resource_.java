package nl.andathen.central.domain.resources;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.andathen.central.domain.AccessLevel;

@Generated(value="Dali", date="2022-07-05T13:19:43.693+0200")
@StaticMetamodel(Resource.class)
public class Resource_ {
	public static volatile SingularAttribute<Resource, Long> id;
	public static volatile SingularAttribute<Resource, String> name;
	public static volatile SingularAttribute<Resource, String> description;
	public static volatile SingularAttribute<Resource, String> playerNotes;
	public static volatile SingularAttribute<Resource, Scarcity> scarcity;
	public static volatile SingularAttribute<Resource, BigDecimal> basePrice;
	public static volatile SingularAttribute<Resource, AccessLevel> accessLevel;
}
