package nl.andathen.central.domain.character;

import java.util.SortedMap;

import nl.andathen.central.domain.person.Player;

public class Character {
	private Player player;
	private String name;
	private String description;
	private SortedMap<Integer, CharacterClass> characterClasses;
	
}
