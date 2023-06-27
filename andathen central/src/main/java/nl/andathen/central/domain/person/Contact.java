package nl.andathen.central.domain.person;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="contact")
public class Contact implements Serializable {
	private static final long serialVersionUID = -8051175764695399655L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="contact_id")
	private Long id;
	@Column(name="firstname", nullable =false)
	private String firstname;
	@Column(name="middlename")
	private String middlename;
	@Column(name="lastname", nullable =false)
	private String lastname;
	@Column(name="email", nullable = false)
	private String email;
	@Column(name="phone_number", nullable = false)
	private String phone;
	@ManyToOne
	@JoinColumn(name="player_id")
	private Player player;
	
	public Contact() {
		
	}

	public Contact(String firstname, String middlename, String lastname, String email, String phone) {
		super();
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
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
