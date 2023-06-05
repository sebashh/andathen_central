package nl.andathen.central.domain.character;

import java.util.LinkedList;
import java.util.Objects;

import nl.andathen.central.domain.person.Player;

public class Character {
	private Long id;
	private Player player;
	private String name;
	private String description;
	private LinkedList<CharacterClass> characterClasses; // Change to only allow uniques?
	
	public Character(Player player, String name, String description) {
		super();
		this.player = player;
		this.name = name;
		this.description = description;
		this.characterClasses = new LinkedList<>();
	}
	
	public Character() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LinkedList<CharacterClass> getCharacterClasses() {
		return characterClasses;
	}

	public void setCharacterClasses(LinkedList<CharacterClass> characterClasses) {
		this.characterClasses = characterClasses;
	}

	public boolean addCharacterClass(CharacterClass cc) {
		return characterClasses.add(cc);
	}
	
	public boolean removeCharacterClass(CharacterClass cc) {
		return characterClasses.remove(cc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, player);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		return Objects.equals(name, other.name) && Objects.equals(player, other.player);
	}

	@Override
	public String toString() {
		return "Character [player=" + player + ", name=" + name + "]";
	}
}
