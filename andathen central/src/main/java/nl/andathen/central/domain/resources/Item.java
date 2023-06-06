package nl.andathen.central.domain.resources;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.character.Skill;

public class Item extends Resource {
	private static final long serialVersionUID = -4677250396785716937L;
	private TreeMap<Resource, Integer> resourcesUsed; // The resource (and hence items) and how much is being used
	private HashSet<Skill> skills; // Skills needed to create this item
	private String recipe; // Textual description.
	
    public Item() {
    	resourcesUsed = new TreeMap<>();
    }

	public Item(Long id, String name, String description, String playerNotes, Scarcity scarcity, BigDecimal basePrice,
			AccessLevel accessLevel, BufferedImage image, String recipe) {
		super(id, name, description, playerNotes, scarcity, basePrice, accessLevel, image);
		this.resourcesUsed = new TreeMap<>();
		this.recipe = recipe;
		this.skills = new HashSet<>();
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public Map<Resource, Integer> getResourcesUsed() {
		return resourcesUsed;
	}

	public void setResourcesUsed(TreeMap<Resource, Integer> resourcesUsed) {
		this.resourcesUsed = resourcesUsed;
	}
	
	public boolean addSkill(Skill skill) {
		return this.skills.add(skill);
	}
	
	public boolean removeSkill(Skill skill) {
		return this.skills.remove(skill);
	}
	
	public HashSet<Skill> getSkills() {
		return skills;
	}

	public void setSkills(HashSet<Skill> skills) {
		this.skills = skills;
	}

	public void addResource(Resource resource) {
		Integer currentAmount = resourcesUsed.get(resource);
		if (resourcesUsed.get(resource) == null) {
			resourcesUsed.put(resource, 1);
		}
		else {
			resourcesUsed.put(resource, currentAmount++);
		}
	}
	
	public void removeResource(Resource resource) {
		Integer currentAmount = resourcesUsed.get(resource);
		if (resourcesUsed.get(resource) != null) {
			if (currentAmount > 1) {
				resourcesUsed.put(resource, currentAmount--);
			}
			else {
				resourcesUsed.remove(resource);
			}
		}
	}

	@Override
	public String toString() {
		return "Item [recipe=" + recipe + ", getName()=" + getName() + ", getId()=" + getId() + "]";
	}
}
