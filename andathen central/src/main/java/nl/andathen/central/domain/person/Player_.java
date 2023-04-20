package nl.andathen.central.domain.person;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-07-05T21:00:48.154+0200")
@StaticMetamodel(Player.class)
public class Player_ {
	public static volatile SingularAttribute<Player, Long> playerNumber;
	public static volatile SingularAttribute<Player, String> firstName;
	public static volatile SingularAttribute<Player, String> middleName;
	public static volatile SingularAttribute<Player, String> lastName;
	public static volatile SingularAttribute<Player, Address> address;
	public static volatile SingularAttribute<Player, LocalDate> dateOfBirth;
	public static volatile SingularAttribute<Player, String> description;
	public static volatile ListAttribute<Player, Contact> contacts;
	public static volatile SingularAttribute<Player, String> password;
	public static volatile SingularAttribute<Player, String> role;
	public static volatile SingularAttribute<Player, String> email;
}
