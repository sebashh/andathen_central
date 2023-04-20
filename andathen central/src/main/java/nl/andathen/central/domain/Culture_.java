package nl.andathen.central.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-08-16T10:52:07.695+0200")
@StaticMetamodel(Culture.class)
public class Culture_ {
	public static volatile SingularAttribute<Culture, Long> id;
	public static volatile SingularAttribute<Culture, String> name;
	public static volatile SingularAttribute<Culture, String> description;
	public static volatile SingularAttribute<Culture, Species> species;
	public static volatile SingularAttribute<Culture, AccessLevel> accessLevel;
}
