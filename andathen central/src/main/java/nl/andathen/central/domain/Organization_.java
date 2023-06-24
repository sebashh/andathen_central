package nl.andathen.central.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-06-05T09:40:37.775+0200")
@StaticMetamodel(Organization.class)
public class Organization_ {
	public static volatile SingularAttribute<Organization, Long> id;
	public static volatile SingularAttribute<Organization, Long> registrationNumber;
	public static volatile SingularAttribute<Organization, String> name;
	public static volatile SingularAttribute<Organization, String> description;
	public static volatile SingularAttribute<Organization, AccessLevel> accessLevel;
}
