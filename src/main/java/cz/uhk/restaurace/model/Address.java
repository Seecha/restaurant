package cz.uhk.restaurace.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Address {
	@Id
	@GeneratedValue
	private int id;
	@NotEmpty
	private String street;
	@NotEmpty
	private String zipCode;
	@NotEmpty
	private String city;
	private String houseNumber;
	@OneToMany(mappedBy="address")
	private Collection<User> user;
	
	public Address() {}

	public Address(String street, String zipCode, String city, String houseNumber) {
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.houseNumber = houseNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	
}
