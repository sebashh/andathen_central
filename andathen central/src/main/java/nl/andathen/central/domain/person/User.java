package nl.andathen.central.domain.person;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User implements Comparable<User>, Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Long id;
	@Column(name="username", unique=true)
	private String username;
	@Column(name="firstname", nullable =false)
	private String firstname;
	@Column(name="middlename", nullable =true)
	private String middlename;
	@Column(name="lastname", nullable =false)
	private String lastname;
	@Column(name="email", nullable = false)
	private String email;
	@Column(name="password", nullable =false)
	private String password; // Hashed version
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Role.class)
	private Set<Role> role = new HashSet<>();
	
	public User (String username, String password, String firstname, String middlename, String lastname, String email,  Role role) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
		this.role.add(role);
	}
	
	public User() {
		
	}

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
        return role;
    }
	
	public boolean addRole(Role role) {
		return this.role.add(role);
	}
	
	public boolean removeRole(Role role) {
		return this.role.remove(role);
	}
    
	public String getMiddlename() {
		return middlename;
	}

	public String getUsername() {
		return username;
	}
	
	public boolean checkCredentials(String password) {
		return password.equals(this.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", middlename=" + middlename
				+ ", lastname=" + lastname + ", email=" + email + "]";
	}

	public int compareTo(User o) {
		if (!this.lastname.equals(o.lastname)) {
			return this.lastname.compareTo(o.lastname);
		}
		else if (!this.firstname.equals(o.firstname)) {
			return this.firstname.compareTo(o.firstname);
		}
		else {
			return this.email.compareTo(o.email);
		}
	}
	
	public enum Role { USER, PLAYER, ADMIN, MEDIC, GAMEMASTER, DEVELOPER, JOURNALIST, PHOTOGRAPHER
		
	}
}