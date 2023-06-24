package nl.andathen.central.domain.person;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-06-05T09:40:37.848+0200")
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
