package nl.andathen.central.domain.resources;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import nl.andathen.central.domain.AccessLevel;

@Entity
@Table(name="item")
public class Item extends Resource {
	private static final long serialVersionUID = -4677250396785716937L;
	
	@ElementCollection(targetClass = Integer.class)
	@MapKeyColumn(name = "item_id", nullable = true)
	@Column(name="amount", nullable=false)
	@CollectionTable(name = "item_resource", joinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
	private Map<Resource, Integer> resourcesUsed;
    
	@ElementCollection(targetClass = Integer.class)
	@MapKeyColumn(name = "item_id", nullable = true)
	@Column(name="amount", nullable=false)
	@CollectionTable(name = "item_item", joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
	private Map<Item, Integer> itemsUsed;
 
    @Column(name="recipe", columnDefinition="TEXT")
	private String recipe;
    
    public Item() {
    	itemsUsed = new TreeMap<>();
    	resourcesUsed = new TreeMap<>();
    }

	public Item(Long id, String name, String description, String playerNotes, Scarcity scarcity, BigDecimal basePrice,
			AccessLevel accessLevel, BufferedImage image, String recipe) {
		super(id, name, description, playerNotes, scarcity, basePrice, accessLevel, image);
		this.itemsUsed = new TreeMap<>();
		this.resourcesUsed = new TreeMap<>();
		this.recipe = recipe;
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

	public Map<Item, Integer> getItemsUsed() {
		return itemsUsed;
	}

	public void setResourcesUsed(Map<Resource, Integer> resourcesUsed) {
		this.resourcesUsed = resourcesUsed;
	}

	public void setItemsUsed(Map<Item, Integer> itemsUsed) {
		this.itemsUsed = itemsUsed;
	}

	@Override
	public String toString() {
		return "Item [recipe=" + recipe + ", getName()=" + getName() + ", getId()=" + getId() + "]";
	}
}
