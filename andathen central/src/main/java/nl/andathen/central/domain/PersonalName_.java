package nl.andathen.central.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-06-05T09:40:37.871+0200")
@StaticMetamodel(PersonalName.class)
public class PersonalName_ {
	public static volatile SingularAttribute<PersonalName, Long> id;
	public static volatile SingularAttribute<PersonalName, String> firstName;
	public static volatile SingularAttribute<PersonalName, String> lastName;
	public static volatile SingularAttribute<PersonalName, Language> language;
	public static volatile SingularAttribute<PersonalName, Gender> gender;
}
