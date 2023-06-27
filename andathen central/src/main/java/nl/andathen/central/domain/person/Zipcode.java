package nl.andathen.central.domain.person;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="zipcode") 
public class Zipcode implements Serializable, Comparable<Zipcode>{
	private static final long serialVersionUID = 7904509798323058935L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="code", unique=true, nullable=false)
	private String code;
	@Column(name="min_number", nullable=true)
	private Integer minNumber;
	@Column(name="max_number", nullable=true)
	private Integer maxNumber;
	@Column(name="street", unique=false, nullable=false)
	private String street;
	@Column(name="municipality", nullable=true)
	private String municipality;
	@Column(name="town", unique=false, nullable=true)
	private String town;
	@Column(name="region", unique=false, nullable=true)
	private String region;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", referencedColumnName="id",nullable=true)
	private Country country;
	
	public Zipcode() {
		
	}
	
	public Zipcode(String code, Integer minNumber, Integer maxNumber, String street, String municipality, String town, String region, Country country) {
		super();
		this.code = code;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		this.street = street;
		this.municipality = municipality;
		this.town = town;
		this.region = region;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(Integer minNumber) {
		this.minNumber = minNumber;
	}

	public Integer getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zipcode other = (Zipcode) obj;
		return Objects.equals(code, other.code);
	}

	@Override
	public String toString() {
		return "Zipcode [code=" + code + ", street=" + street + ", muincipality=" + municipality + "]";
	}

	@Override
	public int compareTo(Zipcode other) {
		return this.code.compareTo(other.getCode());
	}
}
