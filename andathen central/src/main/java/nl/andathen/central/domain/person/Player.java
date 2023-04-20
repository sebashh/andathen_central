package nl.andathen.central.domain.person;

import java.io.Serializable;
import java.time.LocalDate;


import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="player") 
public class Player implements Comparable<Player>, Serializable  {
	/**
	 *
	 */
	private static final long serialVersionUID = 1749157413408852287L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="playerNumber")
	private Long playerNumber;
	@Column(name= "firstName")
	private String firstName;
	@Column(name= "middleName")
	private String middleName;
	@Column(name= "lastName")
	private String lastName;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="house_id")
	private Address address;
	@Column(name= "dateOfBirth")
	private LocalDate dateOfBirth;
	@Column(name= "description")
	private String description;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
	private List<Contact> contacts;
	@Column(name= "password",nullable= false)
	private String password;
	@Column(name="role", nullable=false)
	private String role;
//	@Column (name="medicalinfo")
	@Transient
	private List<String> medicalInfo;
//	@Column (name="dietaryRestrictions")
	@Transient
	private List<String> dietaryRestrictions;
	@Column (name="email")
	private String email;
	

	/**
	* It creates table "player"
	* @param  playerNumber the player_id of the player
	* @param  email the email of the player
	* @param  firstname the firstname of the player
	* @param  lastName the lastName of the player
	* @param  address the address of the player
	* @param  dateOfBirth the dateOfBirth of the player
	* @param  password the password of the player
	* @param  contacts the contacts like email, phone of the user
	* @param  medicalInfo the medicalInfo of the player
	* @param  dietaryRestrictions the dietaryRestrictions of the player
	* @param  role the role of the user
	* @return String the info of the player
	**/
	public Player(Long playerNumber,String firstName,String middleName,String lastName,Address address,LocalDate dateOfBirth,String description,
			List<Contact> contacts,List<String> medicalInfo,List<String> dietaryRestrictions, String role) {
		this.playerNumber=playerNumber;
		this.firstName =firstName;
		this.middleName=middleName;
		this.lastName =lastName;
		this.address=address;
		this.dateOfBirth=dateOfBirth;
		this.description= description;
		this.contacts= contacts;
		this.role= role;
		this.medicalInfo= medicalInfo;
		this.dietaryRestrictions= dietaryRestrictions;
		
	}
	
	public Player(String email,String password)
	{
		this.email= email;
		this.password= password;
	}

	public Player() {
		
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public Long getPlayerNumber() {
		return playerNumber;
	}
	public void setPlayerNumber(Long playerNumber) {
		this.playerNumber = playerNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public List<String> getMedicalInfo() {
		return medicalInfo;
	}
	public void setMedicalInfo(List<String> medicalInfo) {
		this.medicalInfo = medicalInfo;
	}
	public List<String> getDietaryRestrictions() {
		return dietaryRestrictions;
	}
	public void setDietaryRestrictions(List<String> dietaryRestrictions) {
		this.dietaryRestrictions = dietaryRestrictions;
	}

	@Override
	public String toString() {
		return "Player [playerNumber=" + playerNumber + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", description="
				+ description + ", contacts=" + contacts + ", medicalInfo=" + medicalInfo + ", dietaryRestrictions="
				+ dietaryRestrictions + "]";
	}
	@Override
	public int compareTo(Player o) {
		if (!this.getPlayerNumber().equals(o.getPlayerNumber())) {
			return o.getPlayerNumber().compareTo(this.getPlayerNumber());
		}
		else {
			return this.getPlayerNumber().compareTo(o.getPlayerNumber());
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address, contacts, dateOfBirth, description, firstName, lastName, middleName, playerNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(address, other.address) && Objects.equals(contacts, other.contacts)
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(description, other.description)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(middleName, other.middleName) && Objects.equals(playerNumber, other.playerNumber);
	}
}



