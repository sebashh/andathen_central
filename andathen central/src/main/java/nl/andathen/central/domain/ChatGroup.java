package nl.andathen.central.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chatGroup")
public class ChatGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5486368608639253046L;
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="characterName")
	private String characterName;
	@Column(name="groupName")
	private String groupname;
	
	public ChatGroup(String characterName, String groupname) {
		super();
		this.characterName = characterName;
		this.groupname = groupname;
	}
	
	public ChatGroup() {
		
	}

	public long getId() {
		return id;
	}

	public String getCharacterName() {
		return characterName;
	}

	public String getGroupname() {
		return groupname;
	}

	@Override
	public int hashCode() {
		return Objects.hash(characterName, groupname, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatGroup other = (ChatGroup) obj;
		return Objects.equals(characterName, other.characterName) && Objects.equals(groupname, other.groupname)
				&& id == other.id;
	}

	@Override
	public String toString() {
		return "ChatGroup [id=" + id + ", characterName=" + characterName + ", groupname=" + groupname + "]";
	}
	
	
	
	

}
