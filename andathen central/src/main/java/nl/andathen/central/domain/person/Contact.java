package nl.andathen.central.domain.person;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public class Contact implements Serializable {
	private static final long serialVersionUID = -8051175764695399655L;
	private Long id;
	private String firstname;
	private String middlename;
	private String lastname;
	private String email;
	private String phone;
	private HashSet<Player> players;
	
	public Contact() {
		
	}

	public Contact(String firstname, String middlename, String lastname, String email, String phone) {
		super();
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.players = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, phone);
	}
	
	public boolean addPlayer(Player player) {
		return players.add(player);
	}
	
	public boolean removePlayer(Player player) {
		return players.remove(player);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(email, other.email) && Objects.equals(phone, other.phone);
	}
}
