package nl.andathen.central.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-06-05T09:40:37.940+0200")
@StaticMetamodel(Source.class)
public class Source_ {
	public static volatile SingularAttribute<Source, Long> id;
	public static volatile SingularAttribute<Source, Long> registrationNumber;
	public static volatile SingularAttribute<Source, String> name;
	public static volatile SingularAttribute<Source, String> description;
	public static volatile SingularAttribute<Source, AccessLevel> accessLevel;
	public static volatile SingularAttribute<Source, Organization> owner;
}
