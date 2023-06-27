package nl.andathen.central.domain.person;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="address")
public class Address implements Comparable<Address>, Serializable {
	private static final long serialVersionUID = 6451528693699069588L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="address_id")
	private Long id;
	@Column(name="house_number", nullable =false)
	private int houseNumber;
	@Column(name="house_extension")
	private String houseExtension;
	@OneToOne
	@JoinColumn(name="zipcode_id")
	private Zipcode zipcode;
	
	public Address() {
		
	}

	public Address(int houseNumber, String houseExtension, Zipcode zipcode) {
		super();
		this.houseNumber = houseNumber;
		this.houseExtension = houseExtension;
		this.zipcode = zipcode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getHouseExtension() {
		return houseExtension;
	}

	public void setHouseExtension(String houseExtension) {
		this.houseExtension = houseExtension;
	}

	public Zipcode getZipcode() {
		return zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(houseExtension, houseNumber, zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(houseExtension, other.houseExtension) && houseNumber == other.houseNumber
				&& Objects.equals(zipcode, other.zipcode);
	}

	@Override
	public int compareTo(Address o) {
		if (this.zipcode.equals(o.getZipcode()) && (this.houseNumber == o.getHouseNumber())) {
			return this.houseExtension.compareTo(o.getHouseExtension());
		}
		else if (this.zipcode.equals(o.getZipcode())) {
			return this.houseNumber - o.getHouseNumber();
		}
		else {
			return this.zipcode.compareTo(o.getZipcode());
		}
	}

	@Override
	public String toString() {
		return "Address [houseNumber=" + houseNumber + ", houseExtension=" + houseExtension + ", zipcode=" + zipcode
				+ "]";
	}
}

