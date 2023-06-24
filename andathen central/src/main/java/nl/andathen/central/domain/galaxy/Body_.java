package nl.andathen.central.domain.galaxy;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import nl.andathen.central.domain.AccessLevel;

@Generated(value="Dali", date="2023-06-05T09:40:37.557+0200")
@StaticMetamodel(Body.class)
public class Body_ {
	public static volatile SingularAttribute<Body, String> designation;
	public static volatile SingularAttribute<Body, String> name;
	public static volatile SingularAttribute<Body, AccessLevel> accessLevel;
	public static volatile SingularAttribute<Body, BodyImage> bodyImage;
	public static volatile SingularAttribute<Body, String> description;
}
