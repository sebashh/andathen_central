package nl.andathen.central.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-08-02T13:55:23.865+0200")
@StaticMetamodel(Organization.class)
public class Organization_ {
	public static volatile SingularAttribute<Organization, Long> id;
	public static volatile SingularAttribute<Organization, Long> registrationNumber;
	public static volatile SingularAttribute<Organization, String> name;
	public static volatile SingularAttribute<Organization, String> description;
	public static volatile SingularAttribute<Organization, AccessLevel> accessLevel;
}
