package nl.andathen.central.domain.resources;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-07-05T13:19:43.682+0200")
@StaticMetamodel(Item.class)
public class Item_ extends Resource_ {
	public static volatile MapAttribute<Item, Resource, Integer> resourcesUsed;
	public static volatile MapAttribute<Item, Item, Integer> itemsUsed;
	public static volatile SingularAttribute<Item, String> recipe;
}
