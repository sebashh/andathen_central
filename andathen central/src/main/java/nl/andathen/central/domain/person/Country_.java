package nl.andathen.central.domain.person;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-06-05T09:40:37.798+0200")
@StaticMetamodel(Country.class)
public class Country_ {
	public static volatile SingularAttribute<Country, Long> id;
	public static volatile SingularAttribute<Country, String> name;
	public static volatile SingularAttribute<Country, String> alternateName;
	public static volatile SingularAttribute<Country, String> description;
}
