package nl.andathen.central.domain.person;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="player")
public class Player extends User {
	private static final long serialVersionUID = 1749157413408852287L;
	@JoinColumn(name="id")
	private Address address;
	@Column(name="date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	@Column(name="player_description")
	private String description;
	@Column(name="phone_number")
	private String phone;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="player")
	private Set<Contact> contacts;
	@Column(name="medical_info")
	private String medicalInfo;
	@Column(name="dietary_restrictions")
	private String dietaryRestrictions;

	public Player(String username, String password, String firstname, String middlename, String lastname, String email,
					Role role, Address address, LocalDate dateOfBirth, String description, String phone, String medicalInfo,
						String dietaryRestrictions) {
		super(username, password, firstname, middlename, lastname, email, role);
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.description = description;
		this.medicalInfo = medicalInfo;
		this.dietaryRestrictions = dietaryRestrictions;
		this.phone = phone;
		this.contacts = new HashSet<>();
	}
	
	public Player() {
		
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

	public String getMedicalInfo() {
		return medicalInfo;
	}

	public void setMedicalInfo(String medicalInfo) {
		this.medicalInfo = medicalInfo;
	}

	public String getDietaryRestrictions() {
		return dietaryRestrictions;
	}

	public void setDietaryRestrictions(String dietaryRestrictions) {
		this.dietaryRestrictions = dietaryRestrictions;
	}
	
	public boolean addContact(Contact contact) {
		return contacts.add(contact);
	}
	
	public boolean removeContact(Contact contact) {
		return contacts.remove(contact);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Player [dateOfBirth=" + dateOfBirth + ", description=" + description + ", getId()=" + getId()
				+ ", getFirstname()=" + getFirstname() + ", getLastname()=" + getLastname() + ", getEmail()="
				+ getEmail() + "]";
	}
	
}