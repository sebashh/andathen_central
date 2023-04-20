package nl.andathen.central.domain.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-08-10T17:01:29.713+0200")
@StaticMetamodel(Address.class)
public class Address_ {
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, Integer> houseNumber;
	public static volatile SingularAttribute<Address, String> houseExtension;
	public static volatile SingularAttribute<Address, Zipcode> zipcode;
}
