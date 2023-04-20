package nl.andathen.central.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="personal_name", indexes = {	@Index(name = "IDX_PERSONALNAME_FIRSTNAME", columnList = "firstname"),
											@Index(name = "IDX_PERSONALNAME_LASTNAME", columnList = "lastname"),
											@Index(name = "IDX_PERSONALNAME_LANGUAGE", columnList = "language_id")})

public class PersonalName implements Comparable<PersonalName>, Serializable {
	private static final long serialVersionUID = -8914088296176874395L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="firstname", unique=false)
	private String firstName;
	@Column(name="lastname", unique=false)
	private String lastName;
	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;
	@Column(name="gender", nullable=true)
	@Enumerated(EnumType.STRING) 
	private Gender gender;
	
	public PersonalName(Language language, String firstName, String lastName, Gender gender) {
		super();
		this.language = language;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
	
	public PersonalName() {

	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getFirstName(), this.getGender(), this.getLanguage(), this.getLastName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalName other = (PersonalName) obj;
		return Objects.equals(this.getFirstName(), other.getFirstName()) && this.getGender() == other.getGender()
				&& Objects.equals(language, other.language) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() { 
		return "Personal Name: " + this.getFirstName() + " " + this.getLastName();
	}

	@Override
	public int compareTo(PersonalName o) {	
		if (this.getLanguage().equals(o.getLanguage())) {
			if ((this.getLastName().equals(o.getLastName()))) {
				return this.getFirstName().compareTo(o.getFirstName());
			}
			else {
				return this.getLastName().compareTo(o.getLastName());
			}
		}
		else {
			return this.language.compareTo(o.language);
		}
	}
}
