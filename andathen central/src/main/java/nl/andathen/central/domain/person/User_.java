package nl.andathen.central.domain.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.andathen.central.domain.person.User.Role;

@Generated(value="Dali", date="2023-06-02T12:01:20.592+0200")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> middlename;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Role> role;
}
