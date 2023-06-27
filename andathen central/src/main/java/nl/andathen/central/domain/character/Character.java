package nl.andathen.central.domain.character;

import java.math.BigDecimal;
import java.util.*;

import jakarta.persistence.*;
import nl.andathen.central.domain.council.Spaceship;
import nl.andathen.central.domain.person.Player;

@Entity
@Table(name = "character")
public class Character {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Player player;
	@Column(name = "character_name", nullable = false)
	private String name;
	@Column(name = "character_description")
	private String description;

	// LinkedHashSet makes it so it can only contain unique values and also retains insertion order.
	@Transient
	private LinkedHashSet<CharacterClass> characterClasses; // Change to only allow uniques?
	@OneToMany(mappedBy = "character")
	private TreeSet<Skill> skills;
	@OneToOne(mappedBy = "character")
	private Spaceship spaceship;
	@Enumerated(EnumType.STRING)
	private Status status;
	private BigDecimal funds;
	
	public Character(Player player, String name, String description) {
		super();
		this.player = player;
		this.name = name;
		this.description = description;
		this.characterClasses = new LinkedHashSet<>();
		this.skills = new TreeSet<>();
		this.status = Status.CREATED;
		this.funds = BigDecimal.ZERO;
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

	public LinkedHashSet<CharacterClass> getCharacterClasses() {
		return characterClasses;
	}

	public void setCharacterClasses(LinkedHashSet<CharacterClass> characterClasses) {
		this.characterClasses = characterClasses;
	}

	public boolean addCharacterClass(CharacterClass cc) {
		return characterClasses.add(cc);
	}
	
	public boolean removeCharacterClass(CharacterClass cc) {
		return characterClasses.remove(cc);
	}
	
	public boolean addSkill(Skill skill) {
		return skills.add(skill);
	}
	
	public boolean removeSkill(Skill skill) {
		return skills.remove(skill);
	}

	public TreeSet<Skill> getSkills() {
		return skills;
	}

	public void setSkills(TreeSet<Skill> skills) {
		this.skills = skills;
	}

	public Spaceship getSpaceshipOwned() {
		return spaceship;
	}

	public void setSpaceshipOwned(Spaceship spaceship) {
		this.spaceship = spaceship;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		if (this.status.getRank() <= status.getRank()) {
			this.status = status;
		}
	}

	public BigDecimal getFunds() {
		return funds;
	}

	public void setFunds(BigDecimal funds) {
		this.funds = funds;
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
	
	public enum Status { INIT(1), CREATED(2), ACTIVE(3), RETIRED(4), DEAD(5);
		private final int rank;
		
		private Status(int rank) {
			this.rank = rank;
		}

		public int getRank() {
			return rank;
		}
	}
}
