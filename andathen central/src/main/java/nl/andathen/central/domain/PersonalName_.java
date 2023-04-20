package nl.andathen.central.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-08-08T11:51:40.257+0200")
@StaticMetamodel(PersonalName.class)
public class PersonalName_ {
	public static volatile SingularAttribute<PersonalName, Long> id;
	public static volatile SingularAttribute<PersonalName, String> firstName;
	public static volatile SingularAttribute<PersonalName, String> lastName;
	public static volatile SingularAttribute<PersonalName, Language> language;
	public static volatile SingularAttribute<PersonalName, Gender> gender;
}
