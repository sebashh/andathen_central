package nl.andathen.central.domain.character;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-07-05T13:23:17.665+0200")
@StaticMetamodel(Skill.class)
public class Skill_ {
	public static volatile SingularAttribute<Skill, Long> id;
	public static volatile SingularAttribute<Skill, String> name;
	public static volatile SingularAttribute<Skill, String> description;
	public static volatile SingularAttribute<Skill, String> playerNotes;
	public static volatile SingularAttribute<Skill, SkillCategory> category;
	public static volatile SingularAttribute<Skill, Boolean> ageExperience;
	public static volatile SingularAttribute<Skill, Skill> prerequisite;
}
