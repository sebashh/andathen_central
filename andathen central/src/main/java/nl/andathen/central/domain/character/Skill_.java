package nl.andathen.central.domain.character;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-06-05T09:40:37.414+0200")
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
