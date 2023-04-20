package nl.andathen.central.domain.galaxy.planet;

public class OrganismOrder { 
	private String name;
	private String description;
	private OrganismType type;

	public OrganismOrder(String name, OrganismType type, String description) {
		this.name = name;
		this.type = type;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public OrganismType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		OrganismOrder other = (OrganismOrder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
