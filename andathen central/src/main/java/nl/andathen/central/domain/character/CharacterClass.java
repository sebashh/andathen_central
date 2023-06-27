package nl.andathen.central.domain.character;

import java.util.SortedMap;
import java.util.TreeMap;


public class CharacterClass {
	private String name;
	private String description;
	private boolean restricted; // Meaning the class can only be switched to if you have a teacher
	private boolean blocked; // Now only for The bandit class, others could follow
	private SortedMap<Skill, Integer> skillCosts;
	private SortedMap<Skill, Boolean> teacherRequired;

	public CharacterClass(String name, String description, boolean restricted, boolean blocked) {
		super();
		this.skillCosts = new TreeMap<>();
		this.teacherRequired = new TreeMap<>();
		this.name = name;
		this.description = description;
		this.restricted = restricted;
		this.blocked = blocked;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isRestricted() {
		return restricted;
	}

	public boolean isBlocked() {
		return blocked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		CharacterClass other = (CharacterClass) obj;
		if (name == null) {
			return other.name == null;
		} else return name.equals(other.name);
	}

	@Override
	public String toString() {
		return "CharacterClass [name=" + name + ", description=" + description + ", restricted=" + restricted + "]";
	}

	public int addSkill(Skill skill, int costs, boolean teacherRequired) {
		if (this.teacherRequired.put(skill, teacherRequired)) {
			return skillCosts.put(skill, costs);
		} else {
			return -1;
		}
	}

	public int removeSkill(Skill skill) {
		teacherRequired.remove(skill);
		return skillCosts.remove(skill);
	}
}