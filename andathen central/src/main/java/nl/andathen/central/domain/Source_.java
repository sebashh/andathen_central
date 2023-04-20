package nl.andathen.central.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-08-02T14:06:04.748+0200")
@StaticMetamodel(Source.class)
public class Source_ {
	public static volatile SingularAttribute<Source, Long> id;
	public static volatile SingularAttribute<Source, Long> registrationNumber;
	public static volatile SingularAttribute<Source, String> name;
	public static volatile SingularAttribute<Source, String> description;
	public static volatile SingularAttribute<Source, AccessLevel> accessLevel;
	public static volatile SingularAttribute<Source, Organization> owner;
}
