package nl.andathen.central.domain.galaxy.planet;

public class OrganismFamily { 
	private String name;
	private String description;
	private OrganismOrder order;

	public OrganismFamily(String name, OrganismOrder order, String description) {
		this.name = name;
		this.order = order;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public OrganismOrder getOrder() {
		return order;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganismFamily other = (OrganismFamily) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order != other.order)
			return false;
		return true;
	}
}
