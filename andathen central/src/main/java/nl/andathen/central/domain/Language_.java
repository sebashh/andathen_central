package nl.andathen.central.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-07-05T13:19:43.593+0200")
@StaticMetamodel(Language.class)
public class Language_ {
	public static volatile SingularAttribute<Language, Long> id;
	public static volatile SingularAttribute<Language, String> iso;
	public static volatile SingularAttribute<Language, String> name;
	public static volatile SingularAttribute<Language, LanguageType> type;
	public static volatile SingularAttribute<Language, String> description;
}
