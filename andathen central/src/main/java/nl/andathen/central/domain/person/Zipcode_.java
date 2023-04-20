package nl.andathen.central.domain.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-08-14T12:17:10.852+0200")
@StaticMetamodel(Zipcode.class)
public class Zipcode_ {
	public static volatile SingularAttribute<Zipcode, Long> id;
	public static volatile SingularAttribute<Zipcode, String> code;
	public static volatile SingularAttribute<Zipcode, Integer> minNumber;
	public static volatile SingularAttribute<Zipcode, Integer> maxNumber;
	public static volatile SingularAttribute<Zipcode, String> street;
	public static volatile SingularAttribute<Zipcode, String> municipality;
	public static volatile SingularAttribute<Zipcode, String> town;
	public static volatile SingularAttribute<Zipcode, String> region;
	public static volatile SingularAttribute<Zipcode, Country> country;
}
